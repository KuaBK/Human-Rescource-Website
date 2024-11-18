package CNPM.G14.ems.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name ="employee")
public class Employee {
    @Id
    @Column(name = "code")
    int code;

    @OneToOne
    @MapsId
    @JoinColumn(name = "code", referencedColumnName = "code")
    Personnel personnel;

    @Column(name = "task_completed")
    int task_completed;

    @Column(name = "project_involved")
    int project_involved;

    @ManyToOne
    @JoinColumn(name = "departmentID", referencedColumnName = "id", unique = true)
    Department department;

    @ManyToMany(mappedBy = "employees")
    List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Task> tasks = new ArrayList<>();
}
