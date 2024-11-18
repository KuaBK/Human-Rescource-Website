package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.DepartmentRequest;
import CNPM.G14.ems.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest request);
    DepartmentResponse getDepartment(int deptId);
    List<DepartmentResponse> getAllDepartments();
    void deleteDepartment(int deptId);
}
