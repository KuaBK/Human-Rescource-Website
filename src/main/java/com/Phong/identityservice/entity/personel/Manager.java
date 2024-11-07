package com.Phong.identityservice.entity.personel;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
    LocalDateTime manageDate;
}
