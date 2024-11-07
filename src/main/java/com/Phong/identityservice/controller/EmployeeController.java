package com.Phong.identityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Phong.identityservice.entity.personel.Employee;
import com.Phong.identityservice.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // API để tạo một Employee mới
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    // API để cập nhật thông tin Employee
    @PutMapping("/{personelCode}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long personelCode, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(personelCode, employeeDetails);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    // API để lấy thông tin Employee theo personelCode
    @GetMapping("/{personelCode}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long personelCode) {
        Employee employee = employeeService.getEmployeeByPersonelCode(personelCode);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    // API để xóa Employee theo personelCode
    @DeleteMapping("/{personelCode}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long personelCode) {
        employeeService.deleteEmployee(personelCode);
        return ResponseEntity.noContent().build();
    }
}
