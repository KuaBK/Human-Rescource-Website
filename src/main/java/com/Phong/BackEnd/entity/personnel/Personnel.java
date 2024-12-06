package com.Phong.BackEnd.entity.personnel;

import com.Phong.BackEnd.entity.Notification;
import com.Phong.BackEnd.entity.files.File;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.Phong.BackEnd.entity.Account;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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
public class Personnel {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "Id")
    Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", unique = true)
    Long code;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    Position position;

    @NotBlank
    @Column(name = "first_name")
    String firstName;

    @NotBlank
    @Column(name = "last_name")
    String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    Gender gender;

    @NotBlank
    @Email
    @Column(name = "email")
    String email;

    @Size(max = 10)
    @Column(name = "phone")
    String phone;

    @Column(name = "city")
    String city;

    @Column(name = "street")
    String street;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "uploadedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    List<File> files;

    public Personnel(Long code) {
        this.code = code;
    }

    @OneToMany(mappedBy = "sender")
    List<Notification> sentNotifications = new ArrayList<>();

    @ManyToMany(mappedBy = "recipients")
    List<Notification> receivedNotifications = new ArrayList<>();

}
