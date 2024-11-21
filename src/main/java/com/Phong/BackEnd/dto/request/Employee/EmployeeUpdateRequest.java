package com.Phong.BackEnd.dto.request.Employee;

import com.Phong.BackEnd.entity.personel.Position;
import com.Phong.BackEnd.entity.personel.Sex;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeUpdateRequest {
    String email;
    String firstName;
    String lastName;
    String phone;
    String address;
    Sex sex;
    Position position;
    Long departmentId;
}
