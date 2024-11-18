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
@Table(name ="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @Column(name = "description")
    String description;

    @Column(name = "due", nullable = false)
    LocalDate due;

    @Column(name = "status")
    String status;

    @ManyToOne
    @JoinColumn(name = "projectID", referencedColumnName = "id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    Employee employee;
}
