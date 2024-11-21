package com.Phong.BackEnd.dto.response.Project;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectResponse {
    long projectId;
    String projectName;
    String projectDescription;
    String projectStatus;
    String departmentName;
}

