package com.Phong.identityservice.service;

import com.Phong.identityservice.entity.attendance.Attendance;
import com.Phong.identityservice.entity.attendance.Type;
import com.Phong.identityservice.entity.personel.Employee;
import com.Phong.identityservice.exception.AppException;
import com.Phong.identityservice.exception.ErrorCode;
import com.Phong.identityservice.repository.AttendanceRepository;
import com.Phong.identityservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Attendance checkIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
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
        return attendanceRepository.save(attendance);
    }

    @Transactional
    public Attendance checkOut(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeAndDate(employee, today)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_CHECKED_IN));

        if (attendance.getCheckOutTime() != null) {
            throw new AppException(ErrorCode.ALREADY_CHECKED_OUT);
        }

        attendance.setCheckOutTime(LocalDateTime.now());
        attendance.updateDuration();
        attendance.setType(Type.CHECK_OUT);

        return attendanceRepository.save(attendance);
    }

    public String getWorkingDurationForToday(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeAndDate(employee, today)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_CHECKED_IN));

        if (attendance.getDuration() != null) {
            return attendance.getDuration();
        }
        throw new AppException(ErrorCode.DURATION_CALCULATION_ERROR);
    }
}
