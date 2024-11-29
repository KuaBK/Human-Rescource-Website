package com.Phong.BackEnd.dto.response.Manager;

import java.time.LocalDate;

import com.Phong.BackEnd.entity.personnel.Position;
import com.Phong.BackEnd.entity.personnel.Gender;

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
    String city;
    String street;
    String avatar;
    Gender gender;
    Position position;
    String departmentName;
    LocalDate manageDate;
}
