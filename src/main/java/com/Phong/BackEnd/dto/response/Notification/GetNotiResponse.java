package com.Phong.BackEnd.dto.response.Notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetNotiResponse {
    Long id;
    String title;
    String content;
    PersonnelInfo sender;
    String createdAt;
    boolean isRead;
}
