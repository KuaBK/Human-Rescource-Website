package com.Phong.BackEnd.service;

import com.Phong.BackEnd.configuration.SalaryRate;
import com.Phong.BackEnd.dto.request.SalaryBoard.SalaryBoardRequest;
import com.Phong.BackEnd.dto.response.SalaryBoard.SalaryBoardResponse;
import com.Phong.BackEnd.entity.attendance.Attendance;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.salaryBoard.SalaryBoard;
import com.Phong.BackEnd.exception.AppException;
import com.Phong.BackEnd.exception.ErrorCode;
import com.Phong.BackEnd.repository.AttendanceRepository;
import com.Phong.BackEnd.repository.EmployeeRepository;
import com.Phong.BackEnd.repository.SalaryBoardRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalaryBoardService {

    private final SalaryBoardRepository salaryBoardRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    private final SalaryRate salaryProperties;

    private SalaryBoardResponse toSalaryBoardResponse(SalaryBoard salaryBoard) {
        Employee employee = salaryBoard.getEmployee();
        return SalaryBoardResponse.builder()
                .id(salaryBoard.getId())
                .employeeCode(employee.getCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .month(salaryBoard.getMonth())
                .year(salaryBoard.getYear())
                .realPay(salaryBoard.getRealPay())
                .fullWork(salaryBoard.getFullDayNumber())
                .halfWork(salaryBoard.getHalfDayNumber())
                .absence(salaryBoard.getAbsenceDayNumber())
                .bonus(salaryBoard.getBonus())
                .penalty(salaryBoard.getPenalties())
                .build();
    }

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
        salaryBoard.setBonus(0.0);
        salaryBoard.setPenalties(0.0);
        salaryBoard.setFullDayNumber(0);
        salaryBoard.setHalfDayNumber(0);
        salaryBoard.setAbsenceDayNumber(0);
        salaryBoard.setRealPay(0.0);
        salaryBoard = salaryBoardRepository.save(salaryBoard);

        return toSalaryBoardResponse(salaryBoard);
    }

    public SalaryBoardResponse  getSalaryBoard(Long id) {
        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));

        return toSalaryBoardResponse(salaryBoard);
    }

    public List<SalaryBoardResponse> getAllSalaryBoards() {
        return salaryBoardRepository.findAll().stream()
                .map(this::toSalaryBoardResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public SalaryBoardResponse updateSalaryBoard(Long id, SalaryBoard updatedFields) {
        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));

        salaryBoard.setBonus(updatedFields.getBonus());
        salaryBoard.setPenalties(updatedFields.getPenalties());
        salaryBoard.setFullDayNumber(updatedFields.getFullDayNumber());
        salaryBoard.setHalfDayNumber(updatedFields.getHalfDayNumber());
        salaryBoard.setAbsenceDayNumber((updatedFields.getAbsenceDayNumber()));
        salaryBoard.setRealPay(calculateRealPay(
                updatedFields.getFullDayNumber(),
                updatedFields.getHalfDayNumber(),
                updatedFields.getAbsenceDayNumber(),
                updatedFields.getBonus(),
                updatedFields.getPenalties()
        ));

        salaryBoard = salaryBoardRepository.save(salaryBoard);

        return toSalaryBoardResponse(salaryBoard);
    }

    @Transactional
    public void updatePayRate(double newFullWorkPay, double newHalfWorkPay) {
        this.salaryProperties.setFullWorkPay(newFullWorkPay);
        this.salaryProperties.setHalfWorkPay(newHalfWorkPay);

        updateAllSalaryBoards();
    }

    public SalaryBoardResponse updateBonusAndPenalty(Long id, double bonus, double penalty) {
        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));

        salaryBoard.setBonus(bonus);
        salaryBoard.setPenalties(penalty);
        salaryBoard.setRealPay(calculateRealPay(
                salaryBoard.getFullDayNumber(),
                salaryBoard.getHalfDayNumber(),
                salaryBoard.getAbsenceDayNumber(),
                bonus,
                penalty
        ));

        salaryBoard = salaryBoardRepository.save(salaryBoard);
        return toSalaryBoardResponse(salaryBoard);
    }

    public List<SalaryBoardResponse> getAllSalaryBoardsByEmployee(Long employeeCode) {
        Employee employee = employeeRepository.findByCode(employeeCode)
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + employeeCode));

        return salaryBoardRepository.findAllByEmployee(employee).stream()
                .map(this::toSalaryBoardResponse)
                .collect(Collectors.toList());
    }

    public SalaryBoardResponse getSalaryBoardByEmployeeAndDate(Long employeeCode, int month, int year) {
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

    @Transactional
    public void updateAllSalaryBoards() {
        // Retrieve all salary boards
        List<SalaryBoard> salaryBoards = salaryBoardRepository.findAll();
        for (SalaryBoard salaryBoard : salaryBoards) {
            // Recalculate real pay for each salary board
            double newRealPay = calculateRealPay(
                    salaryBoard.getFullDayNumber(),
                    salaryBoard.getHalfDayNumber(),
                    salaryBoard.getAbsenceDayNumber(),
                    salaryBoard.getBonus(),
                    salaryBoard.getPenalties()
            );
            salaryBoard.setRealPay(newRealPay);
        }
        salaryBoardRepository.saveAll(salaryBoards);
    }

    @Transactional
    public void synchronizeSalaryBoard(Long employeeId, int month, int year){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));

        // Generate all dates in the month
        YearMonth yearMonth = YearMonth.of(year, month);
        List<LocalDate> allDatesInMonth = yearMonth.atDay(1)
                .datesUntil(yearMonth.atEndOfMonth().plusDays(1))
                .collect(Collectors.toList());

        // Fetch attendance records for the employee in the given month
        List<Attendance> attendances = attendanceRepository.findAllByMonthAndYear(month, year).stream()
                .filter(attendance -> attendance.getEmployee().equals(employee))
                .collect(Collectors.toList());

        // Map attendance records by date for easier comparison
        Set<LocalDate> attendedDates = attendances.stream()
                .map(Attendance::getDate)
                .collect(Collectors.toSet());

        int fullWorkCount = 0;
        int halfWorkCount = 0;
        int absenceCount = 0;

        // Iterate through all dates in the month
        for (LocalDate date : allDatesInMonth) {
            if (attendedDates.contains(date)) {
                // Attendance exists for this day
                Attendance attendance = attendances.stream()
                        .filter(a -> a.getDate().equals(date))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Attendance not found for date: " + date));

                if (attendance.getCheckInTime() != null && attendance.getCheckOutTime() != null) {
                    Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
                    long minutesWorked = duration.toMinutes();

                    if (minutesWorked > 25) {
                        fullWorkCount++;
                    } else if (minutesWorked > 0) {
                        halfWorkCount++;
                    } else {
                        absenceCount++;
                    }
                } else {
                    absenceCount++;
                }
            } else {
                // No attendance record for this day -> treat as absence
                absenceCount++;
            }
        }

        SalaryBoard salaryBoard = salaryBoardRepository.findByEmployeeAndMonthAndYear(employee, month, year)
                .orElseThrow(() -> new RuntimeException("Salary Board not found"));

        salaryBoard.setFullDayNumber(fullWorkCount);
        salaryBoard.setHalfDayNumber(halfWorkCount);
        salaryBoard.setAbsenceDayNumber(absenceCount);

        salaryBoard.setRealPay(calculateRealPay(
                fullWorkCount,
                halfWorkCount,
                absenceCount,
                salaryBoard.getBonus(),
                salaryBoard.getPenalties()
        ));

        salaryBoardRepository.save(salaryBoard);
    }

}
