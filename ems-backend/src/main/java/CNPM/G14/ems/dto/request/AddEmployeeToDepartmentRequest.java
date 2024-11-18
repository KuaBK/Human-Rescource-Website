package CNPM.G14.ems.dto.request;

import lombok.Data;

@Data
public class AddEmployeeToDepartmentRequest {
    private int empCode;
    private int deptId;
}
