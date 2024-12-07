package com.Phong.BackEnd.dto.response.Notification;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.util.Pair;

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
    LocalDateTime createdAt;
    boolean isRead;
}