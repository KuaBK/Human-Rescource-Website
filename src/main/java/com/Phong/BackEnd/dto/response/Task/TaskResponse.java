package com.Phong.BackEnd.dto.response.Task;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
    Long tasksId;
    String title;
    String description;
    LocalDateTime due;
    String status;
    String projectName;
    String employeeName;
}
