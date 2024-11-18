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
@Table(name ="manager")
public class Manager {
    @Id
    @Column(name = "code")
    int code;

    @OneToOne
    @MapsId
    @JoinColumn(name = "code", referencedColumnName = "code")
    Personnel personnel;

    @Column(name = "manage_date")
    LocalDate manageDate;

    @OneToOne
    @JoinColumn(name = "departmentID", referencedColumnName = "id", unique = true)
    Department department;
}
