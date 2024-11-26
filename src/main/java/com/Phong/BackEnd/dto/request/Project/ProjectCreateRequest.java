package com.Phong.BackEnd.dto.request.Project;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectCreateRequest {
    String projectName;
    String projectDescription;
    String projectStatus;
    Long departmentId;

    @Builder.Default
    Integer participants = 0;
}
