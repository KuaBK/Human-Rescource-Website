package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.request.DepartmentRequest;
import CNPM.G14.ems.dto.response.DepartmentResponse;
import CNPM.G14.ems.entity.Department;
import CNPM.G14.ems.entity.Employee;
import CNPM.G14.ems.entity.Manager;
import CNPM.G14.ems.repository.DepartmentRepository;
import CNPM.G14.ems.repository.EmployeeRepository;
import CNPM.G14.ems.repository.ManagerRepository;
import CNPM.G14.ems.service.DepartmentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public DepartmentResponse createDepartment(DepartmentRequest request){
        Manager manager = managerRepository.findByCode(request.getManagerCode())
                .orElseThrow(() -> new EntityNotFoundException("Manager not found with code: " + request.getManagerCode()));

        Department department = new Department();
        department.setName(request.getDepartmentName());
        department.setEstablishDate(new Date());
        department.setNumberOfEmployees(0);
        department.setManager(manager);

        manager.setDepartment(department);

        departmentRepository.save(department);

        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getNumberOfEmployees(),
                manager.getCode()
        );
    }

    @Override
    public DepartmentResponse getDepartment(int deptId){
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + deptId));

        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getNumberOfEmployees(),
                department.getManager() != null ? department.getManager().getCode() : null
        );
    }

    @Override
    public List<DepartmentResponse> getAllDepartments(){
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> new DepartmentResponse(
                        department.getId(),
                        department.getName(),
                        department.getNumberOfEmployees(),
                        department.getManager() != null ? department.getManager().getCode() : -1
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDepartment(int deptId){
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + deptId));

        if (department.getManager() != null) {
            department.getManager().setDepartment(null);  // Unassign the department from the manager
            managerRepository.save(department.getManager());  // Save updated manager
        }

        for (Employee employee : department.getEmployees()) {
            employee.setDepartment(null);  // Unassign the department from each employee
            employeeRepository.save(employee);  // Save updated employee
        }

        departmentRepository.delete(department);
    }
}
