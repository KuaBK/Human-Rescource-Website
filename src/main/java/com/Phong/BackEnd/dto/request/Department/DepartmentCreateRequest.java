package com.Phong.BackEnd.dto.request.Department;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentCreateRequest {
    @NotBlank(message = "Department name is required")
    String departmentName;
    String address;

    @Builder.Default
    int employeeNumber = 0;

    Long managerId;
}