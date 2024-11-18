package CNPM.G14.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class ProjectEmployeeId implements Serializable {

    @Column(name = "employee_code")
    private int empCode;

    @Column(name = "projectID")
    private int projectId;
}
