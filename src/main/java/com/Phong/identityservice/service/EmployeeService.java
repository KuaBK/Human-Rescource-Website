package com.Phong.identityservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.Phong.identityservice.dto.request.Employee.EmployeeCreateRequest;
import com.Phong.identityservice.dto.request.Employee.EmployeeUpdateRequest;
import com.Phong.identityservice.dto.response.Employee.EmployeeResponse;
import com.Phong.identityservice.entity.Account;
import com.Phong.identityservice.entity.departments.Department;
import com.Phong.identityservice.entity.personel.Position;
import com.Phong.identityservice.entity.personel.Sex;
import com.Phong.identityservice.repository.AccountRepository;
import com.Phong.identityservice.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.identityservice.entity.personel.Employee;
import com.Phong.identityservice.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           AccountRepository accountRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account không tồn tại"));

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));
        }

        Position position = (request.getPosition() != null) ? request.getPosition() : Position.EMPLOYEE;

        Employee employee = Employee.builder()
                .account(account)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .avatar(request.getAvatar())
                .sex(request.getSex())
                .department(department)
                .position(position)
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .personelCode(savedEmployee.getPersonelCode())
                .firstName(savedEmployee.getFirstName())
                .lastName(savedEmployee.getLastName())
                .email(savedEmployee.getEmail())
                .phone(savedEmployee.getPhone())
                .address(savedEmployee.getAddress())
                .avatar(savedEmployee.getAvatar())
                .sex(savedEmployee.getSex())
                .departmentName(department != null ? department.getDepartmentName() : null)
                .position(savedEmployee.getPosition())
                .build();
    }

    public Employee patchEmployee(Long id, EmployeeUpdateRequest updates) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));

        if (updates.getEmail() != null) employee.setEmail(updates.getEmail());
        if (updates.getFirstName() != null) employee.setFirstName(updates.getFirstName());
        if (updates.getLastName() != null) employee.setLastName(updates.getLastName());
        if (updates.getPhone() != null) employee.setPhone(updates.getPhone());
        if (updates.getAddress() != null) employee.setAddress(updates.getAddress());
        if (updates.getSex() != null) employee.setSex(updates.getSex());
        if (updates.getPosition() != null) employee.setPosition(updates.getPosition());
        if (updates.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updates.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));
            employee.setDepartment(department);
        }

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByPersonelCode(Long personelCode) {
        return employeeRepository.findById(personelCode)
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));
    }

    public void deleteEmployee(Long personelCode) {
        employeeRepository.deleteById(personelCode);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeResponse toDto(Employee employee) {
        Department department = employee.getDepartment();
        return EmployeeResponse.builder()
                .personelCode(employee.getPersonelCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .sex(employee.getSex())
                .position(employee.getPosition())
                .departmentName(department != null ? department.getDepartmentName() : null)
                .build();
    }
}
