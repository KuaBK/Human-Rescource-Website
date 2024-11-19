package CNPM.G14.ems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalaryBoardResponse {
    long id;
    int employeeCode;
    int month;
    int year;
    double realPay;
    int fullWork;
    int halfWork;
    int absence;
}
