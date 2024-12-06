package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.request.NotificationRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Notification.NotificationResponse;
import com.Phong.BackEnd.dto.response.Notification.PersonnelInfo;
import com.Phong.BackEnd.entity.Notification;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.personnel.Manager;
import com.Phong.BackEnd.entity.personnel.Personnel;
import com.Phong.BackEnd.repository.EmployeeRepository;
import com.Phong.BackEnd.repository.NotificationRepository;
import com.Phong.BackEnd.repository.PersonnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final PersonnelRepository personnelRepository;
    private final JavaMailSender mailSender;
    private final EmployeeRepository employeeRepository;

    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    public ApiResponse<NotificationResponse> sendNotification(NotificationRequest request) {
        Manager sender = (Manager) personnelRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Department managedDepartment = sender.getDepartment();
        if (managedDepartment == null) {
            throw new RuntimeException("Manager does not manage any department");
        }
        List<Employee> employeesInDepartment = employeeRepository.findAllEmployees().stream()
                .filter(employee -> managedDepartment.equals(employee.getDepartment()))
                .collect(Collectors.toList());

        List<Employee> recipients;
        if (request.isSendToAll()) {
            recipients = employeesInDepartment;
        } else {
            recipients = employeesInDepartment.stream()
                    .filter(employee -> request.getRecipientIds().contains(employee.getCode()))
                    .collect(Collectors.toList());
        }
        if (recipients.isEmpty()) {
            throw new RuntimeException("No valid recipients found in the department");
        }

        Notification notification = Notification.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .sender(sender)
                .recipients(new ArrayList<>(recipients))
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        recipients.forEach(recipient -> {
            String email = recipient.getEmail();
            if (email != null && !email.isEmpty()) {
                        sendEmail(
                        email,
                        request.getTitle(),
                        request.getContent()
                );
            }
        });

        return ApiResponse.<NotificationResponse>builder()
                .code(1000)
                .message("Notification sent successfully")
                .result(NotificationResponse.builder()
                        .id(notification.getId())
                        .title(notification.getTitle())
                        .content(notification.getContent())
                        .sender(PersonnelInfo.builder()
                                .code(sender.getCode())
                                .name(sender.getFirstName() + " " + sender.getLastName())
                                .build())
                        .recipients(recipients.stream().map(r -> PersonnelInfo.builder()
                                        .code(r.getCode())
                                        .name(r.getFirstName() + " " + r.getLastName())
                                        .build())
                                .collect(Collectors.toList()))
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
                        .sender(PersonnelInfo.builder()
                                .code(notification.getSender().getCode())
                                .name(notification.getSender().getFirstName() + " " + notification.getSender().getLastName())
                                .build())
                        .createdAt(notification.getCreatedAt())
                        .build())
                .toList();

        return ApiResponse.<List<NotificationResponse>>builder()
                .code(1000)
                .message("Notifications retrieved successfully")
                .result(notifications)
                .build();
    }
}

