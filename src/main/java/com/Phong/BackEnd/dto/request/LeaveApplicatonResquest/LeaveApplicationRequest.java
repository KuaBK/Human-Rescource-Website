package com.Phong.BackEnd.dto.request.LeaveApplicatonResquest;

import com.Phong.BackEnd.entity.salaryBoard.SalaryBoard;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveApplicationRequest {
    private Long employeeId;
    private SalaryBoard salaryBoard;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
