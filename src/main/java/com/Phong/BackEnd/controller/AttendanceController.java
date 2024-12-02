package com.Phong.BackEnd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Attendance.AttendanceResponse;
import com.Phong.BackEnd.dto.response.Attendance.DurationResponse;
import com.Phong.BackEnd.entity.attendance.Attendance;
import com.Phong.BackEnd.service.AttendanceService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/checkIn")
    public ApiResponse<AttendanceResponse> checkIn(@RequestParam Long code) {
        Attendance attendance = attendanceService.checkIn(code);
        AttendanceResponse responseDTO = convertToResponseDTO(attendance);
        return ApiResponse.<AttendanceResponse>builder()
                .message("Check-in successful")
                .result(responseDTO)
                .build();
    }

    @PostMapping("/checkOut")
    public ApiResponse<AttendanceResponse> checkOut(@RequestParam Long code) {
        Attendance attendance = attendanceService.checkOut(code);
        AttendanceResponse responseDTO = convertToResponseDTO(attendance);
        return ApiResponse.<AttendanceResponse>builder()
                .message("Check-out successful")
                .result(responseDTO)
                .build();
    }

    @GetMapping("/workingDuration")
    public ApiResponse<DurationResponse> getWorkingDurationForToday(@RequestParam Long code) {
        String duration = attendanceService.getWorkingDurationForToday(code);
        DurationResponse responseDTO =
                DurationResponse.builder().duration(duration).build();
        return ApiResponse.<DurationResponse>builder()
                .message("Get Duration Successful")
                .result(responseDTO)
                .build();
    }

    // Lấy tất cả bản ghi attendance của một nhân viên
    @GetMapping("/all/employee")
    public ResponseEntity<ApiResponse<List<Attendance>>> getAllAttendance(
            @RequestParam Long code) {
        List<Attendance> attendances = attendanceService.getAllAttendance(code);
        return ResponseEntity.ok(ApiResponse.<List<Attendance>>builder()
                .code(1000)
                .message("Lấy tất cả attendance thành công!")
                .result(attendances)
                .build());
    }

    // Lấy attendance của một nhân viên theo ngày cụ thể
    @GetMapping("/date")
    public ResponseEntity<ApiResponse<Attendance>> getAttendanceByDate(
            @RequestParam Long code,
            @RequestParam("date") String date) { // Nhập ngày dưới dạng YYYY-MM-DD
        LocalDate localDate = LocalDate.parse(date);
        Attendance attendance = attendanceService.getAttendanceByDate(code, localDate);
        return ResponseEntity.ok(ApiResponse.<Attendance>builder()
                .code(1000)
                .message("Lấy attendance theo ngày thành công!")
                .result(attendance)
                .build());
    }

    // Lấy tất cả attendance hôm nay của tất cả nhân viên
    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendanceToday() {
        List<Attendance> attendances = attendanceService.getAllAttendanceToday();
        List<AttendanceResponse> responses = attendances.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.<List<AttendanceResponse>>builder()
                .code(1000)
                .message("Lấy tất cả attendance hôm nay thành công!")
                .result(responses)
                .build());
    }


    private AttendanceResponse convertToResponseDTO(Attendance attendance) {
        return AttendanceResponse.builder()
                .attendanceId(attendance.getAttendanceId())
                .employeeCode(attendance.getEmployee().getCode())
                .checkInTime(attendance.getCheckInTime())
                .checkOutTime(attendance.getCheckOutTime())
                .duration(attendance.getDuration())
                .employee_code(attendance.getEmployee().getCode())
                .build();
    }
}
