package com.Phong.BackEnd.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.Phong.BackEnd.dto.response.Employee.EWDResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Phong.BackEnd.dto.request.Employee.EmployeeCreateRequest;
import com.Phong.BackEnd.dto.request.Employee.EmployeeUpdateRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Employee.EmployeeResponse;
import com.Phong.BackEnd.entity.personel.Employee;
import com.Phong.BackEnd.repository.AttendanceRepository;
import com.Phong.BackEnd.service.EmployeeService;

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
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeResponse responseDto = employeeService.createEmployee(request);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .message("Employee created successfully")
                .result(responseDto)
                .build());
    }

    @PatchMapping("/{personelCode}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(
            @PathVariable Long personelCode, @RequestBody EmployeeUpdateRequest updates) {
        Employee updatedEmployee = employeeService.patchEmployee(personelCode, updates);
        EmployeeResponse responseDto = employeeService.toDto(updatedEmployee);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .message("Employee updated successfully")
                .result(responseDto)
                .build());
    }

    @GetMapping("/{personelCode}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployee(@PathVariable Long personelCode) {
        Employee employee = employeeService.getEmployeeByPersonelCode(personelCode);
        EmployeeResponse responseDto = employeeService.toDto(employee);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .message("Employee fetched successfully")
                .result(responseDto)
                .build());
    }

    @Transactional
    @DeleteMapping("/{personelCode}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long personelCode) {
        attendanceRepository.deleteByEmployeeCode(personelCode);
        employeeService.deleteEmployee(personelCode);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Employee deleted successfully")
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeResponse> responseDtos =
                employees.stream().map(employeeService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.<List<EmployeeResponse>>builder()
                .message("All employees fetched successfully")
                .result(responseDtos)
                .build());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponse<EWDResponse>> getAllEmployeeInDepartment(
            @PathVariable Long departmentId) {
        EWDResponse ewdResponse = employeeService.getAllEmployeeInDepartment(departmentId);

        return ResponseEntity.ok(ApiResponse.<EWDResponse>builder()
                .code(1000)
                .message("Success")
                .result(ewdResponse)
                .build());
    }

    @PostMapping("/{employeeId}/department/{departmentId}")
    public ResponseEntity<ApiResponse<EWDResponse>> addEmployeeToDepartment(
            @PathVariable Long employeeId,
            @PathVariable Long departmentId) {
        try {
            EWDResponse ewdResponse = employeeService.addEmployeeToDepartment(employeeId, departmentId);
            return ResponseEntity.ok(ApiResponse.<EWDResponse>builder()
                    .code(1000)
                    .message("Employee added to department successfully")
                    .result(ewdResponse)
                    .build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<EWDResponse>builder()
                    .code(4000)
                    .message(ex.getMessage())
                    .result(null)
                    .build());
        }
    }
}
