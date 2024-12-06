package com.Phong.BackEnd.dto.request;

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
    List<Long> recipientIds; // ID của các employee nhận thông báo
    boolean sendToAll;
}

