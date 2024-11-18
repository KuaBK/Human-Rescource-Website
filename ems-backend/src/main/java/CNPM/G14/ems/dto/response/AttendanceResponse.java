package CNPM.G14.ems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceResponse {
    String attendID;
    int employeeID;
    LocalDate date;
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    String timeWorking;
}
