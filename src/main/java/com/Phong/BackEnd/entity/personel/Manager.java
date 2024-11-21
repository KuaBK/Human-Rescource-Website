package com.Phong.BackEnd.entity.personel;

import java.time.LocalDate;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.Phong.BackEnd.entity.departments.Department;
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
    @JoinColumn(name = "department_id")
    Department department;
}
