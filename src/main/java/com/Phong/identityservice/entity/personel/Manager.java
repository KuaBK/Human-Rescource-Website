package com.Phong.identityservice.entity.personel;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import com.Phong.identityservice.entity.departments.Department;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
