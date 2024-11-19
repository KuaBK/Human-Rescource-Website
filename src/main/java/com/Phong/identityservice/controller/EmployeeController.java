package com.Phong.identityservice.controller;

import com.Phong.identityservice.repository.AttendanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Phong.identityservice.entity.personel.Employee;
import com.Phong.identityservice.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, AttendanceRepository attendanceRepository) {
        this.employeeService = employeeService;
        this.attendanceRepository = attendanceRepository;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    @PatchMapping("/{personelCode}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("personelCode") Long id,
            @RequestBody Map<String, Object> updates) {
        Employee updatedEmployee = employeeService.patchEmployee(id, updates);
        return ResponseEntity.ok(updatedEmployee);
    }


    @GetMapping("/{personelCode}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long personelCode) {
        Employee employee = employeeService.getEmployeeByPersonelCode(personelCode);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{personelCode}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long personelCode) {
        attendanceRepository.deleteByEmployeePersonelCode(personelCode);
        employeeService.deleteEmployee(personelCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
