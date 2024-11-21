package com.Phong.BackEnd.entity.personel;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.projects.Projects;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@Table(name = "employee")
public class Employee extends Personel {

    @Builder.Default
    int tasksCompleteNumber = 0;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "emp_proj",
            joinColumns = @JoinColumn(name = "EmployeeCode"),
            inverseJoinColumns = @JoinColumn(name = "ProjectId"))
    Set<Projects> projectList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "departmentID")
    Department department;
}
