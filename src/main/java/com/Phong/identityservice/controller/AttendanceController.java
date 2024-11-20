package com.Phong.identityservice.controller;

import org.springframework.web.bind.annotation.*;

import com.Phong.identityservice.dto.response.ApiResponse;
import com.Phong.identityservice.dto.response.Attendance.AttendanceResponse;
import com.Phong.identityservice.dto.response.Attendance.DurationResponse;
import com.Phong.identityservice.entity.attendance.Attendance;
import com.Phong.identityservice.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/checkIn/{employeeId}")
    public ApiResponse<AttendanceResponse> checkIn(@PathVariable Long employeeId) {
        Attendance attendance = attendanceService.checkIn(employeeId);
        AttendanceResponse responseDTO = convertToResponseDTO(attendance);
        return ApiResponse.<AttendanceResponse>builder()
                .message("Check-in successful")
                .result(responseDTO)
                .build();
    }

    @PostMapping("/checkOut/{employeeId}")
    public ApiResponse<AttendanceResponse> checkOut(@PathVariable Long employeeId) {
        Attendance attendance = attendanceService.checkOut(employeeId);
        AttendanceResponse responseDTO = convertToResponseDTO(attendance);
        return ApiResponse.<AttendanceResponse>builder()
                .message("Check-out successful")
                .result(responseDTO)
                .build();
    }

    @GetMapping("/workingDuration/{employeeId}")
    public ApiResponse<DurationResponse> getWorkingDurationForToday(@PathVariable Long employeeId) {
        String duration = attendanceService.getWorkingDurationForToday(employeeId);
        DurationResponse responseDTO =
                DurationResponse.builder().duration(duration).build();
        return ApiResponse.<DurationResponse>builder()
                .message("Get Duration Successful")
                .result(responseDTO)
                .build();
    }

    private AttendanceResponse convertToResponseDTO(Attendance attendance) {
        return AttendanceResponse.builder()
                .attendanceId(attendance.getAttendanceId())
                .employeeCode(attendance.getEmployee().getPersonelCode())
                .checkInTime(attendance.getCheckInTime())
                .checkOutTime(attendance.getCheckOutTime())
                .duration(attendance.getDuration())
                .build();
    }
}
