package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.request.NotificationRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Notification.NotificationResponse;
import com.Phong.BackEnd.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<NotificationResponse>> sendNotification(
            @RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.sendNotification(request));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getMyNotifications(
            @RequestParam Long personnelId) {
        return ResponseEntity.ok(notificationService.getMyNotifications(personnelId));
    }

    @PostMapping("/{notificationId}/mark")
    public ApiResponse<Void> markAsRead(
            @PathVariable Long notificationId,
            @RequestParam Long personnelId
    ) {
        return notificationService.markAsRead(notificationId, personnelId);
    }
}

