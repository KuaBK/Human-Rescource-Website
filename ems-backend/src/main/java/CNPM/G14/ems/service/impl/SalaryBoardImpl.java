package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.configuration.SalaryRate;
import CNPM.G14.ems.dto.request.SalaryBoardRequest;
import CNPM.G14.ems.dto.response.SalaryBoardResponse;
import CNPM.G14.ems.entity.Employee;
import CNPM.G14.ems.entity.SalaryBoard;
import CNPM.G14.ems.repository.EmployeeRepository;
import CNPM.G14.ems.repository.SalaryBoardRepository;
import CNPM.G14.ems.service.SalaryBoardService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalaryBoardImpl implements SalaryBoardService {

    private final SalaryBoardRepository salaryBoardRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final SalaryRate salaryProperties;

    private SalaryBoardResponse toSalaryBoardResponse(SalaryBoard salaryBoard) {
        return SalaryBoardResponse.builder()
                .id(salaryBoard.getId())
                .employeeCode(salaryBoard.getEmployee().getCode())
                .month(salaryBoard.getMonth())
                .year(salaryBoard.getYear())
                .realPay(salaryBoard.getRealPay())
                .fullWork(salaryBoard.getFullWork())
                .halfWork(salaryBoard.getHalfWork())
                .absence(salaryBoard.getAbsence())
                .build();
    }

    @Override
    @Transactional
    public SalaryBoardResponse createSalaryBoard(SalaryBoardRequest request) {
        Employee employee = employeeRepository.findByCode(request.getEmployeeCode())
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + request.getEmployeeCode()));

        LocalDate now = LocalDate.now();
        int month = request.getMonth() == 0 ? now.getMonthValue() : request.getMonth();
        int year = request.getYear() == 0 ? now.getYear() : request.getYear();

        boolean exists = salaryBoardRepository.existsByEmployeeAndMonthAndYear(employee, month, year);
        if (exists) {
            throw new RuntimeException("Salary board already exists for employee: " + request.getEmployeeCode() +
                    " for month: " + month + " and year: " + year);
        }

        SalaryBoard salaryBoard = new SalaryBoard();
        salaryBoard.setEmployee(employee);
        salaryBoard.setMonth(month);
        salaryBoard.setYear(year);
        salaryBoard.setBonus(0);
        salaryBoard.setPenalty(0);
        salaryBoard.setFullWork(0);
        salaryBoard.setHalfWork(0);
        salaryBoard.setAbsence(0);
        salaryBoard.setRealPay(0);
        salaryBoard = salaryBoardRepository.save(salaryBoard);

        return toSalaryBoardResponse(salaryBoard);
    }

    @Override
    public SalaryBoardResponse  getSalaryBoard(Long id) {
        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));

        return toSalaryBoardResponse(salaryBoard);
    }

    @Override
    @Transactional
    public SalaryBoardResponse updateSalaryBoard(Long id, SalaryBoard updatedFields) {
        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));

        salaryBoard.setBonus(updatedFields.getBonus());
        salaryBoard.setPenalty(updatedFields.getPenalty());
        salaryBoard.setFullWork(updatedFields.getFullWork());
        salaryBoard.setHalfWork(updatedFields.getHalfWork());
        salaryBoard.setAbsence(updatedFields.getAbsence());
        salaryBoard.setRealPay(calculateRealPay(
                updatedFields.getFullWork(),
                updatedFields.getHalfWork(),
                updatedFields.getAbsence(),
                updatedFields.getBonus(),
                updatedFields.getPenalty()
        ));

        salaryBoard = salaryBoardRepository.save(salaryBoard);

        return toSalaryBoardResponse(salaryBoard);
    }

    @Override
    public void updatePayRate(double newFullWorkPay, double newHalfWorkPay) {
        this.salaryProperties.setFullWorkPay(newFullWorkPay);
        this.salaryProperties.setHalfWorkPay(newHalfWorkPay);
    }

    @Override
    public SalaryBoardResponse updateBonusAndPenalty(Long id, double bonus, double penalty) {
        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));

        salaryBoard.setBonus(bonus);
        salaryBoard.setPenalty(penalty);
        salaryBoard.setRealPay(calculateRealPay(
                salaryBoard.getFullWork(),
                salaryBoard.getHalfWork(),
                salaryBoard.getAbsence(),
                bonus,
                penalty
        ));

        salaryBoard = salaryBoardRepository.save(salaryBoard);
        return toSalaryBoardResponse(salaryBoard);
    }

    @Override
    public List<SalaryBoardResponse> getAllSalaryBoardsByEmployee(int employeeCode) {
        Employee employee = employeeRepository.findByCode(employeeCode)
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + employeeCode));

        return salaryBoardRepository.findAllByEmployee(employee).stream()
                .map(this::toSalaryBoardResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SalaryBoardResponse getSalaryBoardByEmployeeAndDate(int employeeCode, int month, int year) {
        Employee employee = employeeRepository.findByCode(employeeCode)
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + employeeCode));

        SalaryBoard salaryBoard = salaryBoardRepository.findByEmployeeAndMonthAndYear(employee, month, year)
                .orElseThrow(() -> new RuntimeException("Salary Board not found for employee: " + employeeCode +
                        ", month: " + month + ", year: " + year));

        return toSalaryBoardResponse(salaryBoard);
    }

    private double calculateRealPay(int fullWork, int halfWork, int absence, double bonus, double penalty) {
        double fullPay = this.salaryProperties.getFullWorkPay();
        double halfPay = this.salaryProperties.getHalfWorkPay();
        double basePay = (fullWork * fullPay) + (halfWork * halfPay);
        double absencePenalty = absence * fullPay;
        return basePay + bonus - penalty - absencePenalty;
    }

}
