package com.Phong.identityservice.controller;

import com.Phong.identityservice.dto.request.ApiResponse;
import com.Phong.identityservice.entity.attendance.Attendance;
import com.Phong.identityservice.service.AttendanceService;
import com.Phong.identityservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/checkIn/{employeeId}")
    public ApiResponse<Attendance> checkIn(@PathVariable Long employeeId) {
        Attendance attendance = attendanceService.checkIn(employeeId);
        return ApiResponse.<Attendance>builder().result(attendance).build();
    }

    @PostMapping("/checkOut/{employeeId}")
    public ApiResponse<Attendance> checkOut(@PathVariable Long employeeId) {
        Attendance attendance = attendanceService.checkOut(employeeId);
        return ApiResponse.<Attendance>builder().result(attendance).build();
    }

    @GetMapping("/workingDuration/{employeeId}")
    public ResponseEntity<String> getWorkingDurationForToday(@PathVariable Long employeeId) {
        try {
            String workingDuration = attendanceService.getWorkingDurationForToday(employeeId);
            return ResponseEntity.ok(workingDuration);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
