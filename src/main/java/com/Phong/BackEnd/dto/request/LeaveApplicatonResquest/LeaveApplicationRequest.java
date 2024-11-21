package com.Phong.BackEnd.dto.request.LeaveApplicatonResquest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveApplicationRequest {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
