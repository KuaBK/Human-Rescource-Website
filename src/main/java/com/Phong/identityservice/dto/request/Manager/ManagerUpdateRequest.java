package com.Phong.identityservice.dto.request.Manager;

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
