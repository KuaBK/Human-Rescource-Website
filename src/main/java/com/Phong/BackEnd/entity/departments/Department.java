package com.Phong.BackEnd.entity.departments;

import java.time.LocalDate;

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

    int employeeNumber;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate establishmentDate;

    String address;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "ManagerCode")
    Manager manager;
}
