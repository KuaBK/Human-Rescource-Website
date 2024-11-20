package com.Phong.identityservice.dto.request.Manager;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.Phong.identityservice.entity.personel.Position;
import com.Phong.identityservice.entity.personel.Sex;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerCreateRequest {
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
