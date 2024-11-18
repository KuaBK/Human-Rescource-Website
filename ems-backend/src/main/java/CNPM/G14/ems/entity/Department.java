package CNPM.G14.ems.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name ="department")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name", unique = true)
    String name;

    @Column(name = "establish_date", updatable = false)
    Date establishDate;

    @Column(name = "number_employees")
    int numberOfEmployees;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Project> projects = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "manager_code", referencedColumnName = "code", unique = true)
    Manager manager;
}
