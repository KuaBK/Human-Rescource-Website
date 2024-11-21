package com.Phong.BackEnd.dto.request.Manager;

import com.Phong.BackEnd.entity.personel.Position;
import com.Phong.BackEnd.entity.personel.Sex;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerUpdateRequest {
    String email;
    String firstName;
    String lastName;
    String phone;
    String address;
    Sex sex;
    Position position;
    Long departmentId;
}
