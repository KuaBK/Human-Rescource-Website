//package CNPM.G14.ems.service.impl;
//
//import CNPM.G14.ems.dto.request.OnLeaveRequest;
//import CNPM.G14.ems.dto.response.OnLeaveResponse;
//import CNPM.G14.ems.entity.Employee;
//import CNPM.G14.ems.entity.OnLeave;
//import CNPM.G14.ems.entity.SalaryBoard;
//import CNPM.G14.ems.repository.EmployeeRepository;
//import CNPM.G14.ems.repository.OnLeaveRepository;
//import CNPM.G14.ems.repository.SalaryBoardRepository;
//import CNPM.G14.ems.service.OnLeaveService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class OnLeaveServiceImpl implements OnLeaveService {
//
//    @Autowired
//    private final OnLeaveRepository onLeaveRepository;
//    private final SalaryBoardRepository salaryBoardRepository;
//    private final EmployeeRepository employeeRepository;
//
//    @Override
//    @Transactional
//    public OnLeaveResponse createOnLeaveLetter(OnLeaveRequest request) {
//        Employee employee = employeeRepository.findByEmployeeCode(request.getEmployeeID())
//                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + request.getEmployeeID()));
//
//        if (request.getStartDate().isAfter(request.getEndDate())) {
//            throw new IllegalArgumentException("Start date cannot be after end date.");
//        }
//
//        LocalDate now = LocalDate.now();
//        SalaryBoard salaryBoard = salaryBoardRepository
//                .findByEmployeeAndMonthAndYear(employee, now.getMonthValue(), now.getYear())
//                .orElseThrow(() -> new RuntimeException("Salary Board not found for the current month and year."));
//
//        OnLeave onLeave = new OnLeave();
//        onLeave.setEmployee(employee);
//        onLeave.setSalaryBoard(salaryBoard);
//        onLeave.setStartDate(request.getStartDate());
//        onLeave.setEndDate(request.getEndDate());
//        onLeave.setReason(request.getReason());
//        onLeave.setStatus("Pending");
//
//        OnLeave savedOnLeave = onLeaveRepository.save(onLeave);
//
//        return new OnLeaveResponse(
//                savedOnLeave.getEmployee().getEmployeeCode(),
//                savedOnLeave.getStartDate(),
//                savedOnLeave.getEndDate(),
//                savedOnLeave.getReason(),
//                savedOnLeave.getStatus()
//        );
//    }
//
//    @Override
//    public List<OnLeaveResponse> getAllOnLeaveByEmployeeId(int employeeId) {
//        List<OnLeave> onLeaves = onLeaveRepository.findAllByEmployee_EmployeeCode(employeeId);
//        return onLeaves.stream()
//                .map(onLeave -> new OnLeaveResponse(
//                        onLeave.getEmployee().getEmployeeCode(),
//                        onLeave.getStartDate(),
//                        onLeave.getEndDate(),
//                        onLeave.getReason(),
//                        onLeave.getStatus()
//                ))
//                .toList();
//        }
//
//}
