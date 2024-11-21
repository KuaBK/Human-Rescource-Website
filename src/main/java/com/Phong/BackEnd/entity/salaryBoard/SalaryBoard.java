package com.Phong.BackEnd.entity.salaryBoard;

import java.time.LocalDate;

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
@Table(name ="salary_board")
public class SalaryBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_code")
    Employee employee;

    Integer month;
    Integer year;

    Double bonus ;
    Double penalties;
    Double realPay;

    Integer halfDayNumber;
    Integer fullDayNumber;
    Integer absenceDayNumber;
}
