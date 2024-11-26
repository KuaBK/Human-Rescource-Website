package com.Phong.BackEnd.entity.leaveApplication;

import java.time.LocalDate;

import com.Phong.BackEnd.entity.salaryBoard.SalaryBoard;
import jakarta.persistence.*;

import com.Phong.BackEnd.entity.personel.Employee;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EmployeeCode")
    Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SalaryId")
    SalaryBoard salaryBoard;

    LocalDate startDate;
    LocalDate endDate;
    String reason;
    Boolean approved;
}
