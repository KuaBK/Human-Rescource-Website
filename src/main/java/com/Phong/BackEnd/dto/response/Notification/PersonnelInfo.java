package com.Phong.BackEnd.dto.response.Notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonnelInfo {
    Long code;
    String name;
}
