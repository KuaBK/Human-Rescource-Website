package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.response.ManagerResponse;
import java.util.List;

public interface ManagerService {
    ManagerResponse assignManagerToDept(int code, int deptId);
    void removeManagerFromDepartment(int deptId);
    int addManager(int code);
    ManagerResponse getManager(int code);
    List<ManagerResponse> getAllManagers();
}
