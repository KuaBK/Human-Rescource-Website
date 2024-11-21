package com.Phong.BackEnd.dto.request.Project;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EIPRequest {
    Long employeeId;
    Long projectId;
}
