package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.request.NotificationRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.NotificationResponse;
import com.Phong.BackEnd.entity.Notification;
import com.Phong.BackEnd.entity.personnel.Manager;
import com.Phong.BackEnd.entity.personnel.Personnel;
import com.Phong.BackEnd.repository.NotificationRepository;
import com.Phong.BackEnd.repository.PersonnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;
    private final PersonnelRepository personnelRepository;

    public ApiResponse<NotificationResponse> sendNotification(NotificationRequest request) {
        Manager sender = (Manager) personnelRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        List<Personnel> recipients;
        if (request.isSendToAll()) {
            recipients = personnelRepository.findAllEmployees(); // Custom query lấy tất cả employee
        } else {
            recipients = personnelRepository.findAllById(request.getRecipientIds());
        }

        Notification notification = Notification.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .sender(sender)
                .recipients(recipients)
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        List<Long> recipientIds = request.isSendToAll() ?
                personnelRepository.findAllEmployees().stream().map(Personnel::getCode).toList() :
                request.getRecipientIds();
        recipientIds.forEach(id -> messagingTemplate.convertAndSend(
                "/topic/notifications/" + id,
                NotificationResponse.builder()
                        .id(notification.getId())
                        .title(notification.getTitle())
                        .content(notification.getContent())
                        .createdAt(notification.getCreatedAt())
                        .build()
        ));


        return ApiResponse.<NotificationResponse>builder()
                .code(1000)
                .message("Notification sent successfully")
                .result(NotificationResponse.builder()
                        .id(notification.getId())
                        .title(notification.getTitle())
                        .content(notification.getContent())
                        .senderName(sender.getFirstName() + " " + sender.getLastName())
                        .recipientNames(recipients.stream()
                                .map(r -> r.getFirstName() + " " + r.getLastName())
                                .toList())
                        .createdAt(notification.getCreatedAt())
                        .build())
                .build();
    }

    public ApiResponse<List<NotificationResponse>> getMyNotifications(Long personnelId) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new RuntimeException("Personnel not found"));

        List<NotificationResponse> notifications = personnel.getReceivedNotifications().stream()
                .map(notification -> NotificationResponse.builder()
                        .id(notification.getId())
                        .title(notification.getTitle())
                        .content(notification.getContent())
                        .isRead(notification.isRead())
                        .senderName(notification.getSender().getFirstName() + " " + notification.getSender().getLastName())
                        .createdAt(notification.getCreatedAt())
                        .build())
                .toList();

        return ApiResponse.<List<NotificationResponse>>builder()
                .code(1000)
                .message("Notifications retrieved successfully")
                .result(notifications)
                .build();
    }

    public ApiResponse<Void> markAsRead(Long notificationId, Long personnelId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (notification.getRecipients().stream().noneMatch(r -> r.getCode().equals(personnelId))) {
            throw new RuntimeException("You are not authorized to mark this notification as read");
        }

        notification.setRead(true);
        notificationRepository.save(notification);

        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Notification marked as read")
                .build();
    }
}

