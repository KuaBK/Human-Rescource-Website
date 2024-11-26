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
@Table(name = "project")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    long projectId;

    @Column(name = "name")
    String projectName;

    @Column(name = "description")
    String projectDescription;

    @Enumerated(EnumType.STRING)
    ProjectStatus projectStatus;

    @ManyToOne
    @JoinColumn(name = "DepartmentId")
    Department department;

    @Column(name = "participants")
    Integer participants;

    @ManyToMany(mappedBy = "projectList")
    Set<Employee> employees;
}
