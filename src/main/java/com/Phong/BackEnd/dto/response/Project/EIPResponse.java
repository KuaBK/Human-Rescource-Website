package com.Phong.BackEnd.dto.response.Project;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EIPResponse {
    Long employeeId;
    String firstName;
    String lastName;
    String email;
}

