package com.Phong.identityservice.entity.attendance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.Phong.identityservice.entity.personel.Employee;

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

    public Duration getTimeWorking() {
        if (checkInTime != null && checkOutTime != null) {
            return Duration.between(checkInTime, checkOutTime);
        }
        return Duration.ZERO; // Trả về Duration mặc định nếu checkInTime hoặc checkOutTime là null
    }

    public Attendance() {
        // Nếu checkInTime hoặc checkOutTime là null, gán giá trị mặc định
        if (checkInTime == null) {
            checkInTime = LocalDateTime.now(); // Ví dụ: giá trị mặc định là thời gian hiện tại
        }
        if (checkOutTime == null) {
            checkOutTime = LocalDateTime.now(); // Ví dụ: giá trị mặc định là thời gian hiện tại
        }
    }
}
