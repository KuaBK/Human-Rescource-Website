package com.Phong.BackEnd.dto.request.Task;

import lombok.experimental.FieldDefaults;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {
    String title;
    String description;
    LocalDateTime deadline;
    String status;
    Long projectId;
    Long employeeId;
}
