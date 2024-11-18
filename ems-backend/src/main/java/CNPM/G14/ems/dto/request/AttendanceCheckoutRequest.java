package CNPM.G14.ems.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceCheckoutRequest {
    String attendID;
    int employeeID;
    LocalDate checkoutDate;
    LocalTime checkoutTime;
}
