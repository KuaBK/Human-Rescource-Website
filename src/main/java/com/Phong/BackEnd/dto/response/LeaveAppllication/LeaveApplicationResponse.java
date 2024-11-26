package com.Phong.BackEnd.dto.response.LeaveAppllication;

import com.Phong.BackEnd.entity.salaryBoard.SalaryBoard;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LeaveApplicationResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private Boolean approved;
}
