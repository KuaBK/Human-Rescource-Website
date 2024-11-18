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
@Table(name ="employee_in_project")
public class EmployeeInProject {
    @EmbeddedId
    ProjectEmployeeId id = new ProjectEmployeeId();

    @ManyToOne
    @MapsId("empCode") // Maps empCode in ProjectEmployeeId to the Employee entity
    @JoinColumn(name = "employee_code")
    Employee employee;

    @ManyToOne
    @MapsId("projectId") // Maps projectId in ProjectEmployeeId to the Project entity
    @JoinColumn(name = "projectID")
    Project project;
}
