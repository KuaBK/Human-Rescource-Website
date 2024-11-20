package com.Phong.identityservice.dto.response.Manager;

import java.time.LocalDate;

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
public class ManagerResponse {
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
    LocalDate manageDate;
}
