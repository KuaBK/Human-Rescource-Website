package com.Phong.identityservice.entity.leaveApplication;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.Phong.identityservice.entity.personel.Employee;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class LeaveApplication {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EmployeeId")
    Employee employee;

    LocalDate startDate;
    LocalDate endDate;
    String reason;
}
