package CNPM.G14.ems.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnLeaveRequest {
    int employeeID;
    LocalDate startDate;
    LocalDate endDate;
    String reason;
}
