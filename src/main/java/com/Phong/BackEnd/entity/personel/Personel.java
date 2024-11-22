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
@Table(name = "personel")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personel {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountId", referencedColumnName = "AccountId")
    Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonelCode", unique = true)
    Long personelCode;

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
    @Column(name = "Sex")
    Sex sex;

    // LocalDate dob;

    @NotBlank
    @Email
    @Column(name = "Email")
    String email;

    @Size(max = 10)
    @Column(name = "Phone")
    String phone;

    @Column(name = "Address")
    String address;

    @Column(name = "Avatar")
    private String avatar;

    @OneToMany(mappedBy = "uploadedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    List<File> files;
}
