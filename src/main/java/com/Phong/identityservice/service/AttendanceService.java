package com.Phong.identityservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.identityservice.entity.attendance.Attendance;
import com.Phong.identityservice.entity.personel.Employee;
import com.Phong.identityservice.repository.AttendanceRepository;
import com.Phong.identityservice.repository.EmployeeRepository;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository; // Để tìm Employee theo PersonelCode

    public Attendance checkIn(Long personelCode) {
        // Tìm Employee theo PersonelCode
        Optional<Employee> employeeOpt = employeeRepository.findByPersonelCode(personelCode);
        if (employeeOpt.isEmpty()) {
            throw new RuntimeException("Employee not found.");
        }

        Employee employee = employeeOpt.get();

        // Kiểm tra xem nhân viên đã check-in chưa
        Optional<Attendance> existingCheckIn = attendanceRepository.findByEmployee_PersonelCode(personelCode);
        if (existingCheckIn.isPresent()) {
            throw new RuntimeException("Employee has already checked in but not checked out.");
        }

        // Tạo mới Attendance và gán thông tin
        Attendance checkInOut = new Attendance();
        checkInOut.setEmployee(employee); // Gán Employee vào Attendance
        checkInOut.setCheckInTime(LocalDateTime.now()); // Gán thời gian check-in

        // Lưu Attendance
        return attendanceRepository.save(checkInOut);
    }

    public Attendance checkOut(Long personelCode) {
        // Tìm Employee theo PersonelCode
        Optional<Employee> employeeOpt = employeeRepository.findByPersonelCode(personelCode);
        if (employeeOpt.isEmpty()) {
            throw new RuntimeException("Employee not found.");
        }

        Employee employee = employeeOpt.get();

        // Kiểm tra xem nhân viên đã check-in chưa
        Optional<Attendance> checkInOutOpt = attendanceRepository.findByEmployee_PersonelCode(personelCode);
        if (checkInOutOpt.isEmpty()) {
            throw new RuntimeException("Employee has not checked in or has already checked out.");
        }

        Attendance checkInOut = checkInOutOpt.get();
        checkInOut.setCheckOutTime(LocalDateTime.now()); // Gán thời gian check-out

        // Lưu Attendance với thời gian check-out mới
        return attendanceRepository.save(checkInOut);
    }
}
