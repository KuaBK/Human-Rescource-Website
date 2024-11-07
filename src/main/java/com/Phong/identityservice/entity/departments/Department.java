package com.Phong.identityservice.entity.departments;

import java.time.LocalDate;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.Phong.identityservice.entity.personel.Manager;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long departmentId;

    String departmentName;
    int employeeNumber;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate establishmentDate;

    @OneToOne
    @JoinColumn(name = "ManagerCode")
    Manager manager;
}
