package com.Phong.BackEnd.entity.attendance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import com.Phong.BackEnd.entity.personnel.Employee;

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
    @Column(name = "Id")
    long attendanceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    Employee employee;

    @Enumerated(EnumType.STRING)
    Type type;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "check_in_time")
    LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    LocalDateTime checkOutTime;

    @Column(name = "duration")
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
