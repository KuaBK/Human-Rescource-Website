package com.Phong.identityservice.entity.personel;

import java.util.Set;

import com.Phong.identityservice.entity.departments.Department;
import jakarta.persistence.*;

import com.Phong.identityservice.entity.projects.Projects;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employee")
public class Employee extends Personel {

    int tasksCompleteNumber;

    @ManyToMany
    @JoinTable(
            name = "emp_proj",
            joinColumns = @JoinColumn(name = "EmployeeCode"),
            inverseJoinColumns = @JoinColumn(name = "ProjectId"))
    Set<Projects> projectList;

    @ManyToOne
    @JoinColumn(name = "departmentID")
    Department department;
}
