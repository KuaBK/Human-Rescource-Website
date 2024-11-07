package com.Phong.identityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Phong.identityservice.entity.attendance.Attendance;
import com.Phong.identityservice.service.AttendanceService;

@RestController
@RequestMapping("/api/check")
public class AttendanceController {
    @Autowired
    private AttendanceService AttendanceService;

    @PostMapping("/in/{employeeId}")
    public Attendance checkIn(@PathVariable Long PersonelCode) {
        return AttendanceService.checkIn(PersonelCode);
    }

    @PostMapping("/out/{employeeId}")
    public Attendance checkOut(@PathVariable Long PersonelCode) {
        return AttendanceService.checkOut(PersonelCode);
    }
}
