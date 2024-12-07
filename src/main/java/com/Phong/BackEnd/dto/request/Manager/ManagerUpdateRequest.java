package com.Phong.BackEnd.dto.request.Manager;

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
public class ManagerUpdateRequest {
    String firstName;
    String lastName;
    String email;
    Gender gender;
    String phone;
    String city;
    String street;
}
