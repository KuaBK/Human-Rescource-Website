//package CNPM.G14.ems.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Builder
//
//@Entity
//@Table(name ="attendance")
//public class Attendance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "attendanceID")
//    String attendanceID;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "employee_code", referencedColumnName = "emp_code")
//    Employee employee;
//
//    @ManyToOne
//    @JoinColumn(name = "salaryID", referencedColumnName = "id")
//    SalaryBoard salaryBoard;
//
//    @Column(name = "workdate")
//    LocalDate date;
//
//    @Column(name = "check_in")
//    LocalDateTime checkIn;
//
//    @Column(name = "check_out")
//    LocalDateTime checkOut;
//
//    @Column(name = "time_working")
//    Duration timeWorking;
//
//    public Duration getTimeWorking() {
//        if(checkIn != null && checkOut != null) {
//            return Duration.between(checkIn, checkOut);
//        }
//        return Duration.ZERO;
//    }
//
//    public Attendance(){
//        if(checkIn == null) {
//            checkIn = LocalDateTime.now();
//        }
//        if(checkOut == null) {
//            checkOut = LocalDateTime.now();
//        }
//    }
//}
