package com.Phong.BackEnd.dto.response.Department;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentResponse {
    Long departmentId;
    String departmentName;
    int employeeNumber;
    LocalDate establishmentDate;
    Long managerId;
}

