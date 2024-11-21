package com.Phong.BackEnd.dto.request.Department;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentUpdateRequest {
    String departmentName;
    String address;

    Integer employeeNumber;

    Long managerId;
}
