package com.Phong.BackEnd.dto.request.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.Phong.BackEnd.entity.personnel.Position;
import com.Phong.BackEnd.entity.personnel.Gender;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeCreateRequest {
    @NotBlank
    String accountId;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    @Email
    String email;

    @Size(max = 10)
    String phone;

    String city;

    String street;

    Position position;

    String avatar;

    Gender gender;

    Long departmentId;
}
