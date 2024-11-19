package CNPM.G14.ems.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name ="onleave")
public class OnLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "salaryID", referencedColumnName = "id")
    SalaryBoard salaryBoard;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    LocalDate endDate;

    @Column(name = "reason")
    String reason;

    @Column(name = "status")
    String status;
}
