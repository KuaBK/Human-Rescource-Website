package com.Phong.BackEnd.entity.notification;

import com.Phong.BackEnd.entity.personnel.Manager;
import com.Phong.BackEnd.entity.personnel.Personnel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.FieldDefaults;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm - dd/MM/yyyy")
    LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "notification_recipient",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    List<Personnel> recipients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sender_id")
    Manager sender;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationStatus> notificationStatuses = new ArrayList<>();
}
