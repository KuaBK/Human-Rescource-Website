package com.Phong.identityservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.Phong.identityservice.entity.departments.Department;
import com.Phong.identityservice.entity.personel.Position;
import com.Phong.identityservice.entity.personel.Sex;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.identityservice.entity.personel.Employee;
import com.Phong.identityservice.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee patchEmployee(Long id, Map<String, Object> updates) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "email":
                    employee.setEmail((String) value);
                    break;
                case "firstName":
                    employee.setFirstName((String) value);
                    break;
                case "lastName":
                    employee.setLastName((String) value);
                    break;
                case "position":
                    employee.setPosition((Position) value);
                    break;
                case "sex":
                    employee.setSex((Sex) value);
                    break;
                case "phone":
                    employee.setPhone((String) value);
                    break;
                case "address":
                    employee.setAddress((String) value);
                    break;
                case "department":
                    employee.setDepartment((Department) value);
                    break;
                default:
                    throw new IllegalArgumentException("Trường " + key + " không hợp lệ");
            }
        });

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByPersonelCode(Long personelCode) {
        return employeeRepository.findById(personelCode).orElse(null);
    }

    public void deleteEmployee(Long personelCode) {
        employeeRepository.deleteById(personelCode);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
