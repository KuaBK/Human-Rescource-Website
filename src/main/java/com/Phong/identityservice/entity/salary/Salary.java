package com.Phong.identityservice.entity.salary;

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
public class Salary {
    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EmployeeId")
    Employee employee;

    LocalDate month;
    LocalDate year;
    long bonus = 0;
    long penalties = 0;
    long salary = 0;

    long halfDayNumber = 0;
    long fullDayNumber = 0;
    long absenceDayNumber = 0;
}
