package CNPM.G14.ems.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name ="salary_board")
public class SalaryBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    Employee employee;

    @Column(name = "month")
    int month;

    @Column(name = "year")
    int year;

    @Column(name = "bonus")
    double bonus;

    @Column(name = "penalty")
    double penalty;

    @Column(name = "real_pay")
    double realPay;

    @Column(name = "full_work")
    int fullWork;

    @Column(name = "half_work")
    int halfWork;

    @Column(name = "absence")
    int absence;
}
