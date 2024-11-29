package com.Phong.BackEnd.entity.salaryBoard;

import jakarta.persistence.*;

import com.Phong.BackEnd.entity.personnel.Employee;

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

    @Column(name = "month")
    Integer month;

    @Column(name = "year")
    Integer year;

    @Column(name = "bonus")
    Double bonus ;

    @Column(name = "penalties")
    Double penalties;

    @Column(name = "real_pay")
    Double realPay;

    @Column(name = "half_day_number")
    Integer halfDayNumber;

    @Column(name = "full_day_number")
    Integer fullDayNumber;

    @Column(name = "absence_day_number")
    Integer absenceDayNumber;
}
