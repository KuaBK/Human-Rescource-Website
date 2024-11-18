package CNPM.G14.ems.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
}
