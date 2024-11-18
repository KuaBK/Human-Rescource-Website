//package CNPM.G14.ems.service.impl;
//
//import CNPM.G14.ems.dto.request.SalaryBoardRequest;
//import CNPM.G14.ems.dto.response.SalaryBoardResponse;
//import CNPM.G14.ems.entity.Employee;
//import CNPM.G14.ems.entity.SalaryBoard;
//import CNPM.G14.ems.repository.EmployeeRepository;
//import CNPM.G14.ems.repository.SalaryBoardRepository;
//import CNPM.G14.ems.service.SalaryBoardService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@AllArgsConstructor
//public class SalaryBoardImpl implements SalaryBoardService {
//
//    private final SalaryBoardRepository salaryBoardRepository;
//    private final EmployeeRepository employeeRepository;
//
//    private SalaryBoardResponse toSalaryBoardResponse(SalaryBoard salaryBoard) {
//        return SalaryBoardResponse.builder()
//                .id(salaryBoard.getId())
//                .employeeCode(salaryBoard.getEmployee().getEmployeeCode())
//                .month(salaryBoard.getMonth())
//                .year(salaryBoard.getYear())
//                .realPay(salaryBoard.getRealPay())
//                .build();
//    }
//
//    @Override
//    @Transactional
//    public SalaryBoardResponse createSalaryBoard(SalaryBoardRequest request) {
//        Employee employee = employeeRepository.findById(request.getEmployeeCode())
//                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + request.getEmployeeCode()));
//
//        SalaryBoard salaryBoard = new SalaryBoard();
//        salaryBoard.setEmployee(employee);
//        salaryBoard.setMonth(request.getMonth());
//        salaryBoard.setYear(request.getYear());
//        salaryBoard.setBonus(0);
//        salaryBoard.setPenalty(0);
//        salaryBoard.setFullWork(0);
//        salaryBoard.setHalfWork(0);
//        salaryBoard.setAbsence(0);
//        salaryBoard.setRealPay(0);
//        salaryBoard = salaryBoardRepository.save(salaryBoard);
//
//        return toSalaryBoardResponse(salaryBoard);
//    }
//
//    @Override
//    public SalaryBoardResponse  getSalaryBoard(Long id) {
//        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));
//
//        return toSalaryBoardResponse(salaryBoard);
//    }
//
//    @Override
//    @Transactional
//    public SalaryBoardResponse updateSalaryBoard(Long id, SalaryBoard updatedFields) {
//        SalaryBoard salaryBoard = salaryBoardRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Salary Board not found with ID: " + id));
//
//        // Update only fields provided in the request
//        salaryBoard.setBonus(updatedFields.getBonus());
//        salaryBoard.setPenalty(updatedFields.getPenalty());
//        salaryBoard.setFullWork(updatedFields.getFullWork());
//        salaryBoard.setHalfWork(updatedFields.getHalfWork());
//        salaryBoard.setAbsence(updatedFields.getAbsence());
//        salaryBoard.setRealPay(calculateRealPay(
//                updatedFields.getFullWork(),
//                updatedFields.getHalfWork(),
//                updatedFields.getAbsence(),
//                updatedFields.getBonus(),
//                updatedFields.getPenalty()
//        ));
//
//        salaryBoard = salaryBoardRepository.save(salaryBoard);
//
//        return toSalaryBoardResponse(salaryBoard);
//    }
//
//    private double calculateRealPay(int fullWork, int halfWork, int absence, double bonus, double penalty) {
//        final double FULL_WORK_PAY = 100.0; // Example value for full work day pay
//        final double HALF_WORK_PAY = 50.0;  // Example value for half work day pay
//
//        double basePay = (fullWork * FULL_WORK_PAY) + (halfWork * HALF_WORK_PAY);
//        double absencePenalty = absence * FULL_WORK_PAY;
//
//        return basePay + bonus - penalty - absencePenalty;
//    }
//
//}
