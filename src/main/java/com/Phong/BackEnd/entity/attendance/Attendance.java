package com.Phong.BackEnd.entity.attendance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.Phong.BackEnd.entity.personel.Employee;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long attendanceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EmployeeCode", referencedColumnName = "personel_code")
    Employee employee;

    @Enumerated(EnumType.STRING)
    Type type;

    LocalDate date;

    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;

    String duration;

    public void updateDuration() {
        if (checkInTime != null && checkOutTime != null) {
            Duration workDuration = Duration.between(checkInTime, checkOutTime);
            this.duration = formatDuration(workDuration);
        } else {
            this.duration = "00:00:00";
        }
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public Attendance() {
        if (checkInTime == null) {
            checkInTime = LocalDateTime.now();
        }
        if (checkOutTime == null) {
            checkOutTime = LocalDateTime.now();
        }
    }
}
