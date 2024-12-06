package com.Phong.BackEnd.dto.response.SalaryBoard;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalaryBoardResponse {
    Long id;
    Long employeeCode;
    String firstName;
    String lastName;
    Integer month;
    Integer year;
    Double realPay;
    Integer fullWork;
    Integer halfWork;
    Integer absence;
    Double bonus;
    Double penalty;
}