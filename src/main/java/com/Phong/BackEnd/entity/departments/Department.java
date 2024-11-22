package com.Phong.BackEnd.entity.departments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Phong.BackEnd.entity.personel.Employee;
import com.Phong.BackEnd.entity.projects.Projects;
import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.Phong.BackEnd.entity.personel.Manager;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(
        name = "department",
        uniqueConstraints = {@UniqueConstraint(columnNames = "departmentName")})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long departmentId;

    @Column(unique = true)
    String departmentName;

    @Column(name = "employee_number")
    int employeeNumber = 0;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate establishmentDate;

    String address;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "ManagerCode", unique = true)
    Manager manager;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Projects> projects = new ArrayList<>();
}
