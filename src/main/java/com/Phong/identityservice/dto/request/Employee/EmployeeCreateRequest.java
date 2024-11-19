package com.Phong.identityservice.dto.request.Employee;

import com.Phong.identityservice.entity.personel.Position;
import com.Phong.identityservice.entity.personel.Sex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    String address;

    Position position;

    String avatar;

    Sex sex;

    Long departmentId;
}
