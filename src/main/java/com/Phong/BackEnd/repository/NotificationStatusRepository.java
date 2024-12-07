package com.Phong.BackEnd.repository;

import com.Phong.BackEnd.entity.notification.Notification;
import com.Phong.BackEnd.entity.notification.NotificationStatus;
import com.Phong.BackEnd.entity.personnel.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationStatusRepository extends JpaRepository<NotificationStatus, Long> {
    List<NotificationStatus> findByEmployee(Employee employee);
    List<NotificationStatus> findByNotification(Notification notification);
    NotificationStatus findByNotificationAndEmployee(Notification notification, Employee employee);
}
