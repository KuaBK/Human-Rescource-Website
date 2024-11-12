package com.Phong.identityservice.entity.personel;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.Phong.identityservice.entity.departments.Department;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Manager extends Personel {
    @Column(name = "ManageDate")
    LocalDate manageDate;

    @OneToOne
    @JoinColumn(name = "departmentID")
    Department department;
}
