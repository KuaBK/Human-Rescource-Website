//package CNPM.G14.ems.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.time.LocalDate;
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//
//@Entity
//@Table(name ="onleave")
//public class OnLeave {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "onleaveID")
//    String onleaveID;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "employee_code", referencedColumnName = "emp_code")
//    Employee employee;
//
//    @ManyToOne
//    @JoinColumn(name = "salaryID", referencedColumnName = "id")
//    SalaryBoard salaryBoard;
//
//    @Column(name = "startDate")
//    LocalDate startDate;
//
//    @Column(name = "endDate", nullable = false)
//    LocalDate endDate;
//
//    @Column(name = "reason")
//    String reason;
//
//    // @Column(name = "status")
//    String status;
//}
