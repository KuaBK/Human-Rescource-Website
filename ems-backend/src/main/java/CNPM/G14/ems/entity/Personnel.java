package CNPM.G14.ems.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name ="personnel")
public class Personnel {
    @Id
    @Column(name = "code")
    int code;

    @Column(name = "position")
    String position;

    @NotBlank
    @Column(name = "first_name")
    String firstName;

    @NotBlank
    @Column(name = "last_name")
    String lastName;

    @Column(name = "gender")
    String gender;

    @Column(name = "email")
    @Email
    String email;

    @Column(name = "city")
    String city;

    @Column(name = "street")
    String street;

    @Size(max = 10)
    @Column(name = "phone")
    String phone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountID", referencedColumnName = "id")
    Account account;
}
