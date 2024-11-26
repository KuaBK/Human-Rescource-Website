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

    // Lấy tất cả bản ghi attendance của một nhân viên
    @GetMapping("/all/{employeeId}")
    public ResponseEntity<ApiResponse<List<Attendance>>> getAllAttendance(
            @PathVariable Long employeeId) {
        List<Attendance> attendances = attendanceService.getAllAttendance(employeeId);
        return ResponseEntity.ok(ApiResponse.<List<Attendance>>builder()
                .code(1000)
                .message("Lấy tất cả attendance thành công!")
                .result(attendances)
                .build());
    }

    // Lấy attendance của một nhân viên theo ngày cụ thể
    @GetMapping("/date/{employeeId}")
    public ResponseEntity<ApiResponse<Attendance>> getAttendanceByDate(
            @PathVariable Long employeeId,
            @RequestParam("date") String date) { // Nhập ngày dưới dạng YYYY-MM-DD
        LocalDate localDate = LocalDate.parse(date);
        Attendance attendance = attendanceService.getAttendanceByDate(employeeId, localDate);
        return ResponseEntity.ok(ApiResponse.<Attendance>builder()
                .code(1000)
                .message("Lấy attendance theo ngày thành công!")
                .result(attendance)
                .build());
    }

    // Lấy tất cả attendance hôm nay của tất cả nhân viên
    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<Attendance>>> getAllAttendanceToday() {
        List<Attendance> attendances = attendanceService.getAllAttendanceToday();
        return ResponseEntity.ok(ApiResponse.<List<Attendance>>builder()
                .code(1000)
                .message("Lấy tất cả attendance hôm nay thành công!")
                .result(attendances)
                .build());
    }


    private AttendanceResponse convertToResponseDTO(Attendance attendance) {
        return AttendanceResponse.builder()
                .attendanceId(attendance.getAttendanceId())
                .employeeCode(attendance.getEmployee().getCode())
                .checkInTime(attendance.getCheckInTime())
                .checkOutTime(attendance.getCheckOutTime())
                .duration(attendance.getDuration())
                .build();
    }
}
