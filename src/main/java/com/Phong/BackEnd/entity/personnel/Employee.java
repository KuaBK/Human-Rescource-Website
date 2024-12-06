package com.Phong.BackEnd.entity.personnel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Phong.BackEnd.entity.Notification;
import com.Phong.BackEnd.entity.tasks.Tasks;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Employee extends Personnel {

    @Builder.Default
    int tasksCompleteNumber = 0;

    @Builder.Default
    int project_involved = 0;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "emp_proj",
            joinColumns = @JoinColumn(name = "employee_code"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    Set<Projects> projectList = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Tasks> taskList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "departmentID")
    Department department;
}
