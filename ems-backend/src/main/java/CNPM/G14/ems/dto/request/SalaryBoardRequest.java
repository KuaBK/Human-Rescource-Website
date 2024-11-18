package CNPM.G14.ems.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryBoardRequest {
    private int employeeCode;
    private int month;
    private int year;
}
