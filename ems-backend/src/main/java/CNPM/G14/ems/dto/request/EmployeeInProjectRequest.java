package CNPM.G14.ems.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeInProjectRequest {
    int employeeCode;
    int projectCode;
}
