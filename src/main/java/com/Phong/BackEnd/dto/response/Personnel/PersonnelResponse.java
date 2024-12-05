package com.Phong.BackEnd.dto.response.Personnel;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonnelResponse {
    Long code;
    String firstName;
    String lastName;
    String email;
    String phone;
    String position;
    String gender;
    String city;
    String street;
    String avatar;
    String departmentName;
    Long departmentID;
}
