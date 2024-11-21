package com.Phong.BackEnd.entity.projects;

import java.util.Set;

import jakarta.persistence.*;

import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personel.Employee;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectId")
    long projectId;

    String projectName;
    String projectDescription;

    @Enumerated(EnumType.STRING)
    ProjectStatus projectStatus;

    @ManyToOne
    @JoinColumn(name = "DepartmentId")
    Department department;

    @ManyToMany(mappedBy = "projectList")
    Set<Employee> employees;
}
