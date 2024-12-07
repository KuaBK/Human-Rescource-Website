package com.Phong.BackEnd.dto.response.Notification;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponse {
    Long id;
    String title;
    String content;
    PersonnelInfo sender;
    List<PersonnelInfo> recipients;
    String createdAt;
    boolean isRead;
}
