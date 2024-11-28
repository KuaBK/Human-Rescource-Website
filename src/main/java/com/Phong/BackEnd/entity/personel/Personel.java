package com.Phong.BackEnd.entity.personel;

import com.Phong.BackEnd.entity.files.File;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.Phong.BackEnd.entity.Account;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "personnel")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personel {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountId", referencedColumnName = "Id")
    Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code", unique = true)
    Long code;

    @Enumerated(EnumType.STRING)
    @Column(name = "Position")
    Position position;

    @NotBlank
    @Column(name = "FirstName")
    String firstName;

    @NotBlank
    @Column(name = "LastName")
    String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    Gender gender;
    // LocalDate dob;

    @NotBlank
    @Email
    @Column(name = "Email")
    String email;

    @Size(max = 10)
    @Column(name = "Phone")
    String phone;

    @Column(name = "City")
    String city;

    String street;

    @Column(name = "Avatar")
    private String avatar;

    @OneToMany(mappedBy = "uploadedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    List<File> files;
}
