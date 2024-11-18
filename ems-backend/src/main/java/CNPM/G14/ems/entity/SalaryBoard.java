//package CNPM.G14.ems.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name ="salary_board")
//public class SalaryBoard {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    long id;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "emp_code", referencedColumnName = "emp_code")
//    Employee employee;
//
//    @Column(name = "month")
//    int month;
//
//    @Column(name = "year")
//    int year;
//
//    @Column(name = "bonus")
//    double bonus;
//
//    @Column(name = "penalty")
//    double penalty;
//
//    @Column(name = "real_pay")
//    double realPay;
//
//    @Column(name = "fullwork")
//    int fullWork;
//
//    @Column(name = "halfwork")
//    int halfWork;
//
//    @Column(name = "absence")
//    int absence;
//}
