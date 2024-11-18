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
@Table(name ="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "participants")
    int participants;

    @ManyToOne
    @JoinColumn(name = "departmentID", referencedColumnName = "id")
    Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_in_project",
            joinColumns = @JoinColumn(name = "projectID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_code", referencedColumnName = "code")
    )
    List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Task> tasks = new ArrayList<>();
}

