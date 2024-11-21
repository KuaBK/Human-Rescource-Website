package com.Phong.BackEnd.dto.request.SalaryBoard;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalaryBoardRequest {
    Long employeeCode;
    Integer month = 0;
    Integer year = 0;
}
