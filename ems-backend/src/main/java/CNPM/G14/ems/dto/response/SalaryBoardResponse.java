package CNPM.G14.ems.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryBoardResponse {
    private long id;
    private int employeeCode;
    private int month;
    private int year;
    private double realPay;
}
