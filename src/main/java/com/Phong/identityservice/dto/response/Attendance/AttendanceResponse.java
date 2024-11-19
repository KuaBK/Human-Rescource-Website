package com.Phong.identityservice.dto.response.Attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponse {
    private long attendanceId;
    private Long employeeCode;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String duration;
}
