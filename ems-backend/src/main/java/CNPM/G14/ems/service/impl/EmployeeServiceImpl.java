package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.response.EmployeeResponse;
import CNPM.G14.ems.entity.Department;
import CNPM.G14.ems.entity.Employee;
import CNPM.G14.ems.entity.Personnel;
import CNPM.G14.ems.exception.ResourceNotFoundException;
import CNPM.G14.ems.repository.DepartmentRepository;
import CNPM.G14.ems.repository.EmployeeRepository;

import CNPM.G14.ems.repository.PersonnelRepository;
import CNPM.G14.ems.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PersonnelRepository personelRepository;

    private EmployeeResponse toEmployeeResponse(Employee employee){
        return EmployeeResponse.builder()
                .employeeCode(employee.getCode())
                .projectInvolved(employee.getProject_involved())
                .taskCompleted(employee.getTask_completed())
                .deptId(employee.getDepartment().getId())
                .build();
    }

    @Override
    @Transactional
    public EmployeeResponse addEmployee(int code, int deptId) {
        Personnel personel = personelRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Personel not found with code: " + code));
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + deptId));

        Employee employee = new Employee();
        employee.setPersonnel(personel);
        employee.setTask_completed(0);
        employee.setProject_involved(0);
        employee.setDepartment(department);

        department.getEmployees().add(employee);
        department.setNumberOfEmployees(department.getNumberOfEmployees() + 1);

        return toEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public EmployeeResponse addEmployeeToDepartment(int code, int deptId){
        Employee employee = employeeRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with code: " + code));
        Department newDepartment = departmentRepository.findById(deptId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + deptId));

        Department currentDept = employee.getDepartment();
        if (currentDept != null && currentDept.getId() != deptId) {
            currentDept.getEmployees().remove(employee);
            currentDept.setNumberOfEmployees(currentDept.getNumberOfEmployees() - 1);
            departmentRepository.save(currentDept);
        }

        employee.setDepartment(newDepartment);
        newDepartment.getEmployees().add(employee);
        newDepartment.setNumberOfEmployees(newDepartment.getNumberOfEmployees() + 1);

        employeeRepository.save(employee);
        departmentRepository.save(newDepartment);

        return toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse getEmployee(int code) {
        Employee employee =  employeeRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with code: " + code));

        return toEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployee(){
        return employeeRepository.findAll().stream()
                .map(this::toEmployeeResponse)
                .toList();
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartmentId(int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId).stream()
                .map(this::toEmployeeResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteEmployee(int code) {
        Employee employee =  employeeRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with code: " + code));

        Department department = employee.getDepartment();
        if (department != null) {
            department.getEmployees().remove(employee);
            department.setNumberOfEmployees(department.getNumberOfEmployees() - 1);
            departmentRepository.save(department);
        }
        employeeRepository.deleteById(code);
    }

}

