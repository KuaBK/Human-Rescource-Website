package com.Phong.BackEnd.dto.request.Notification;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRequest {
    String title;
    String content;
    Long senderId;
    List<Long> recipientIds;
    boolean sendToAll;
}
