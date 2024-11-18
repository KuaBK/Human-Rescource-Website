package CNPM.G14.ems.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PersonelResponse {
    Integer code;
    String position;
    String firstName;
    String lastName;
    String sex;
    String email;
    String username;
    String password;
    String city;
    String street;
    String phone;
    String deptName;
    Integer deptId;
}
