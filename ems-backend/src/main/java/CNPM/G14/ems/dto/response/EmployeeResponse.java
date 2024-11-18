package CNPM.G14.ems.dto.response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    int employeeCode;
    int taskCompleted;
    int projectInvolved;
    int deptId;
}
