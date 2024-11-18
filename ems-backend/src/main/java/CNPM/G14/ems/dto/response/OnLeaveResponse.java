package CNPM.G14.ems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnLeaveResponse {
    int employeeID;
    LocalDate startDate;
    LocalDate endDate;
    String reason;
    String status;
}
