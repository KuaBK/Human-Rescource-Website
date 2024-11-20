package com.Phong.identityservice.entity.personel;

import java.time.LocalDate;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.Phong.identityservice.entity.departments.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "manager")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Manager extends Personel {
    @CreationTimestamp
    @Column(name = "ManageDate")
    LocalDate manageDate;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "departmentID")
    Department department;
}
