package com.Phong.BackEnd.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Phong.BackEnd.configuration.SalaryRate;
import com.Phong.BackEnd.dto.response.Attendance.AttendanceResponse2;
import com.Phong.BackEnd.entity.salaryBoard.SalaryBoard;
import com.Phong.BackEnd.repository.SalaryBoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Phong.BackEnd.entity.attendance.Attendance;
import com.Phong.BackEnd.entity.attendance.Type;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.exception.AppException;
import com.Phong.BackEnd.exception.ErrorCode;
import com.Phong.BackEnd.repository.AttendanceRepository;
import com.Phong.BackEnd.repository.EmployeeRepository;
import java.util.LinkedHashMap;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final SalaryBoardRepository salaryBoardRepository;
    private final SalaryRate salaryProperties;

    @Transactional
    public Attendance checkIn(Long employeeId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        LocalDate today = LocalDate.now();
        Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeAndDate(employee, today);
        if (existingAttendance.isPresent()) {
            throw new AppException(ErrorCode.ALREADY_CHECKED_IN);
        }
        Attendance attendance = Attendance.builder()
                .employee(employee)
                .date(today)
                .checkInTime(LocalDateTime.now())
                .type(Type.CHECK_IN)
                .build();
        attendanceRepository.save(attendance);
        return attendance;
    }

    @Transactional
    public Attendance checkOut(Long employeeId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository
                .findByEmployeeAndDate(employee, today)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_CHECKED_IN));

        if (attendance.getCheckOutTime() != null) {
            throw new AppException(ErrorCode.ALREADY_CHECKED_OUT);
        }

        attendance.setCheckOutTime(LocalDateTime.now());
        attendance.updateDuration();
        attendance.setType(Type.CHECK_OUT);

        updateSalaryBoard(employee);

        return attendanceRepository.save(attendance);
    }

    public String getWorkingDurationForToday(Long employeeId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository
                .findByEmployeeAndDate(employee, today)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_CHECKED_IN));

        if (attendance.getDuration() != null) {
            return attendance.getDuration();
        }
        throw new AppException(ErrorCode.DURATION_CALCULATION_ERROR);
    }

    // Lấy attendance của 1 nhân viên vào 1 ngày cụ thể
    public Attendance getAttendanceByDate(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        return attendanceRepository.findByEmployeeAndDate(employee, date)
                .orElseThrow(() -> new AppException(ErrorCode.ATTENDANCE_NOT_FOUND));
    }

    // Lấy tất cả attendance hôm nay của tất cả nhân viên
    public List<Attendance> getAllAttendanceToday() {
        LocalDate today = LocalDate.now();
        return attendanceRepository.findByDate(today);
    }

    // Lấy tất cả attendance của 1 nhân viên
    public List<Attendance> getAllAttendance(Long employeeId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        return attendanceRepository.findByEmployee(employee);
    }

    public List<AttendanceResponse2> getAttendanceForMonth(int month, int year){
        List<Attendance> attendances = attendanceRepository.findAllByMonthAndYear(month, year);

        return attendances.stream()
                .collect(Collectors.groupingBy(
                        attendance -> attendance.getEmployee().getCode(),
                        LinkedHashMap::new, // Preserve insertion order
                        Collectors.mapping(attendance -> AttendanceResponse2.AttendanceRecord.builder()
                                        .attendanceId(attendance.getAttendanceId())
                                        .attendanceResult(determineAttendanceResult(attendance))
                                        .attendanceDate(java.sql.Date.valueOf(attendance.getDate()))
                                        .build(),
                                Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    Employee employee = employeeRepository.findByCode(entry.getKey())
                            .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
                    return AttendanceResponse2.builder()
                            .employeeName(employee.getFirstName() + " " + employee.getLastName())
                            .employeeCode(entry.getKey())
                            .attendanceRecords(entry.getValue())
                            .build();
                })
                .toList();
    }

    private String determineAttendanceResult(Attendance attendance) {
        if (attendance.getDuration() == null) {
            return "absence";
        }
        Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
        long minutesWorked = duration.toMinutes();

        if (minutesWorked > 25) {
            return "full day work";
        } else if (minutesWorked > 0) {
            return "half day work";
        } else {
            return "absence";
        }
    }

    private double calculateRealPay(int fullWork, int halfWork, int absence, double bonus, double penalty) {
        double fullPay = this.salaryProperties.getFullWorkPay();
        double halfPay = this.salaryProperties.getHalfWorkPay();
        double basePay = (fullWork * fullPay) + (halfWork * halfPay);
        double absencePenalty = absence * fullPay;
        return basePay + bonus - penalty - absencePenalty;
    }

    @Transactional
    public void updateSalaryBoard(Employee employee) {
        // Lấy bảng lương của nhân viên
        LocalDate today = LocalDate.now();
        SalaryBoard salaryBoard = salaryBoardRepository.findByEmployeeAndMonthAndYear(
                        employee, today.getMonthValue(), today.getYear())
                .orElseGet(() -> SalaryBoard.builder()
                        .employee(employee)
                        .month(today.getMonthValue())
                        .year(today.getYear())
                        .bonus(0.0)
                        .penalties(0.0)
                        .realPay(0.0)
                        .halfDayNumber(0)
                        .fullDayNumber(0)
                        .absenceDayNumber(0)
                        .build());

        Attendance attendance = attendanceRepository
                .findByEmployeeAndDate(employee, today)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_CHECKED_IN));

        Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
        long secondsWorked = duration.getSeconds();

        if (secondsWorked >= 10) {
            salaryBoard.setFullDayNumber(salaryBoard.getFullDayNumber() + 1);
        } else if (secondsWorked >= 5) {
            salaryBoard.setHalfDayNumber(salaryBoard.getHalfDayNumber() + 1);
        } else {
            salaryBoard.setAbsenceDayNumber(salaryBoard.getAbsenceDayNumber() + 1);
        }
        salaryBoard.setRealPay(calculateRealPay(
                salaryBoard.getFullDayNumber(),
                salaryBoard.getHalfDayNumber(),
                salaryBoard.getAbsenceDayNumber(),
                salaryBoard.getBonus(),
                salaryBoard.getPenalties()
        ));
        salaryBoardRepository.save(salaryBoard);
    }
}

