package com.Phong.identityservice.service;

import java.util.Optional;

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

    public Employee updateEmployee(Long personelCode, Employee employeeDetails) {
        Optional<Employee> employeeOptional = employeeRepository.findById(personelCode);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhone(employeeDetails.getPhone());
            employee.setAddress(employeeDetails.getAddress());
            employee.setPosition(employeeDetails.getPosition());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public Employee getEmployeeByPersonelCode(Long personelCode) {
        return employeeRepository.findById(personelCode).orElse(null);
    }

    public void deleteEmployee(Long personelCode) {
        employeeRepository.deleteById(personelCode);
    }
}
