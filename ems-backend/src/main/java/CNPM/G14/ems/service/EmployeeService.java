package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse addEmployeeToDepartment(int code, int deptId);
    EmployeeResponse addEmployee(int code, int deptId);
    EmployeeResponse getEmployee(int code);
    List<EmployeeResponse> getAllEmployee();
    List<EmployeeResponse> getEmployeesByDepartmentId(int deptId);
    void deleteEmployee(int personelCode);
}
