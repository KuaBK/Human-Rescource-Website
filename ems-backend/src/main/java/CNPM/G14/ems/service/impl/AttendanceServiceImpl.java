//package CNPM.G14.ems.service.impl;
//
//import CNPM.G14.ems.entity.Attendance;
//import CNPM.G14.ems.entity.Employee;
//import CNPM.G14.ems.entity.SalaryBoard;
//import CNPM.G14.ems.exception.AppException;
//import CNPM.G14.ems.exception.ErrorCode;
//import CNPM.G14.ems.repository.AttendanceRepository;
//import CNPM.G14.ems.repository.EmployeeRepository;
//import CNPM.G14.ems.repository.SalaryBoardRepository;
//import CNPM.G14.ems.service.AttendanceService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class AttendanceServiceImpl implements AttendanceService {
//
//    @Autowired
//    private final AttendanceRepository attendanceRepository;
//    private final EmployeeRepository employeeRepository;
//    private final SalaryBoardRepository salaryBoardRepository;
//
//    private Employee getEmployeeById(int employeeId) {
//        return employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
//    }
//
//    private Attendance getTodayAttendance(int employeeId) {
//        LocalDate today = LocalDate.now();
//        return attendanceRepository.findByEmployee_EmployeeCodeAndDate(employeeId, today)
//                .orElseThrow(() -> new AppException(ErrorCode.NOT_CHECKED_IN));
//    }
//
//    private Attendance createAttendanceForEmployee(Employee employee, SalaryBoard salaryBoard) {
//        return Attendance.builder()
//                .employee(employee)
//                .salaryBoard(salaryBoard)
//                .date(LocalDate.now())
//                .checkIn(LocalDateTime.now())
//                .checkOut(null)
//                .build();
//    }
//
//    private SalaryBoard getOrCreateSalaryBoard(Employee employee) {
//        LocalDate today = LocalDate.now();
//        Optional<SalaryBoard> salaryBoard = salaryBoardRepository.findByEmployeeAndMonthAndYear(employee, today.getMonthValue(), today.getYear());
//
//        return salaryBoard.orElseGet(() -> {
//            SalaryBoard newSalaryBoard = new SalaryBoard();
//            newSalaryBoard.setEmployee(employee);
//            newSalaryBoard.setMonth(today.getMonthValue());
//            newSalaryBoard.setYear(today.getYear());
//            newSalaryBoard.setFullWork(0);
//            newSalaryBoard.setHalfWork(0);
//            newSalaryBoard.setAbsence(0);
//            salaryBoardRepository.save(newSalaryBoard);
//            return newSalaryBoard;
//        });
//    }
//
//    private void updateSalaryBoard(Attendance attendance) {
//        SalaryBoard salaryBoard = attendance.getSalaryBoard();
//        Duration timeWorking = attendance.getTimeWorking();
//
//        // Check if the attendance counts as full work, half work, or absence
//        if (timeWorking.toHours() >= 8) {
//            salaryBoard.setFullWork(salaryBoard.getFullWork() + 1);
//        } else if (timeWorking.toHours() >= 4) {
//            salaryBoard.setHalfWork(salaryBoard.getHalfWork() + 1);
//        } else {
//            salaryBoard.setAbsence(salaryBoard.getAbsence() + 1);
//        }
//
//        salaryBoardRepository.save(salaryBoard);
//    }
//
//    private String formatDuration(Duration duration) {
//        long hours = duration.toHours();
//        long minutes = duration.toMinutes() % 60;
//        long seconds = duration.getSeconds() % 60;
//        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
//    }
//
//    @Override
//    public Attendance getAttendance(String id) {
//        return attendanceRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.ATTENDANCE_NOT_FOUND));
//    }
//
//    @Override
//    public Attendance getSpecificAttendance(int employeeId, int year, int month, int day) {
//        return attendanceRepository.findByEmployee_EmployeeCodeAndDate(employeeId, LocalDate.of(year, month, day))
//                .orElseThrow(() -> new IllegalArgumentException("Attendance not found for the specified date and employee ID."));
//    }
//
//    @Override
//    public List<Attendance> getAttendancesByEmployeeId(int employeeId){
//        List<Attendance> attendances = attendanceRepository.findAllByEmployee_EmployeeCode(employeeId);
//
//        if (attendances.isEmpty()) {
//            throw new IllegalArgumentException("No attendance records found for the given employee ID.");
//        }
//
//        return attendances;
//    }
//
//    @Override
//    @Transactional
//    public Attendance checkIn(int employeeId) {
//        Employee employee = getEmployeeById(employeeId);
//        LocalDate today = LocalDate.now();
//
//        // Check if the employee has already checked in today
//        if (attendanceRepository.findByEmployee_EmployeeCodeAndDate(employeeId, today).isPresent()) {
//            throw new AppException(ErrorCode.ALREADY_CHECKED_IN);
//        }
//
//        SalaryBoard salaryBoard = getOrCreateSalaryBoard(employee);
//        Attendance attendance = createAttendanceForEmployee(employee, salaryBoard);
//
//        return attendanceRepository.save(attendance);
//    }
//
//    @Override
//    @Transactional
//    public Attendance checkOut(int employeeId) {
//        Attendance attendance = getTodayAttendance(employeeId);
//
//        // Ensure the employee hasn't already checked out
//        if (attendance.getCheckOut() != null) {
//            throw new AppException(ErrorCode.ALREADY_CHECKED_OUT);
//        }
//
//        attendance.setCheckOut(LocalDateTime.now());
//        attendance.setTimeWorking(Duration.between(attendance.getCheckIn(), attendance.getCheckOut()));
//
//        updateSalaryBoard(attendance);
//
//        return attendanceRepository.save(attendance);
//    }
//
//    @Override
//    public String getWorkingDurationForToday(int employeeId) {
//        Attendance attendance = getTodayAttendance(employeeId);
//
//        // Ensure that both checkIn and checkOut are not null before calculating duration
//        if (attendance.getCheckIn() == null || attendance.getCheckOut() == null) {
//            throw new AppException(ErrorCode.DURATION_CALCULATION_ERROR);
//        }
//
//        // Ensure checkOut is after checkIn
//        if (attendance.getCheckOut().isBefore(attendance.getCheckIn())) {
//            throw new AppException(ErrorCode.DURATION_CALCULATION_ERROR);
//        }
//
//        Duration duration = Duration.between(attendance.getCheckIn(), attendance.getCheckOut());
//        return formatDuration(duration);
//    }
//}
