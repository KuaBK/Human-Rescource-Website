package com.Phong.identityservice.dto.response.Employee;

import com.Phong.identityservice.entity.departments.Department;
import com.Phong.identityservice.entity.personel.Position;
import com.Phong.identityservice.entity.personel.Sex;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    Long personelCode;

    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    String avatar;
    Sex sex;
    Position position;

    String departmentName;
}
