package com.Phong.BackEnd.dto.response.Attendance;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceResponse2 {
    String employeeName;
    Long employeeCode;
    List<AttendanceRecord> attendanceRecords;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AttendanceRecord {
        Long attendanceId;
        String attendanceResult;
        Date attendanceDate;
    }
}
