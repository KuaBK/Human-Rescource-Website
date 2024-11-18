package CNPM.G14.ems.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonnelCreationRequest {
    String accountId;
    String firstName;
    String lastName;
    String position;
    String gender;
    String email;
    String city;
    String street;
    String phoneNumber;
}
