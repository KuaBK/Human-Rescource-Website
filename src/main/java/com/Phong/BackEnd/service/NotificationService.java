package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.request.Notification.NotificationRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Notification.GetNotiResponse;
import com.Phong.BackEnd.dto.response.Notification.NotificationResponse;
import com.Phong.BackEnd.dto.response.Notification.PersonnelInfo;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.notification.Notification;
import com.Phong.BackEnd.entity.notification.NotificationStatus;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.personnel.Manager;
import com.Phong.BackEnd.repository.EmployeeRepository;
import com.Phong.BackEnd.repository.NotificationRepository;
import com.Phong.BackEnd.repository.NotificationStatusRepository;
import com.Phong.BackEnd.repository.PersonnelRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final PersonnelRepository personnelRepository;
    private final JavaMailSender mailSender;
    private final EmployeeRepository employeeRepository;
    private final NotificationStatusRepository notificationStatusRepository;

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
            NotificationStatus status = NotificationStatus.builder()
                    .notification(notification)
                    .employee(recipient)
                    .isRead(false)
                    .build();
            notificationStatusRepository.save(status);
        });

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");

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
                        .createdAt(notification.getCreatedAt().format(formatter))
                        .build())
                .build();
    }

    public ApiResponse<List<GetNotiResponse>> getMyNotifications(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        List<GetNotiResponse> notifications = notificationStatusRepository.findByEmployee(employee).stream()
                .map(status -> {
                    Notification notification = status.getNotification();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");
                    return GetNotiResponse.builder()
                            .id(notification.getId())
                            .title(notification.getTitle())
                            .content(notification.getContent())
                            .isRead(status.isRead())
                            .sender(PersonnelInfo.builder()
                                    .code(notification.getSender().getCode())
                                    .name(notification.getSender().getFirstName() + " " + notification.getSender().getLastName())
                                    .build())
                            .createdAt(notification.getCreatedAt().format(formatter))
                            .build();
                })
                .toList();

        return ApiResponse.<List<GetNotiResponse>>builder()
                .code(1000)
                .message("Notifications retrieved successfully")
                .result(notifications)
                .build();
    }

    public ApiResponse<Void> markAsRead(Long notificationId, Long personnelId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        Employee employee = employeeRepository.findById(personnelId)
                .orElseThrow(() -> new RuntimeException("Personnel not found"));

        NotificationStatus status = notificationStatusRepository.findByNotificationAndEmployee(notification, employee);
        if (status == null) {
            throw new RuntimeException("No status found for this notification and personnel");
        }

        status.setRead(true);
        notificationStatusRepository.save(status);

        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Notification marked as read")
                .build();
    }

}
