package com.Phong.BackEnd.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.Phong.BackEnd.dto.response.Employee.EWDResponse;
import jakarta.persistence.EntityNotFoundException;
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
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.repository.AttendanceRepository;
import com.Phong.BackEnd.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, AttendanceRepository attendanceRepository) {
        this.employeeService = employeeService;
        this.attendanceRepository = attendanceRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeResponse responseDto = employeeService.createEmployee(request);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .message("Employee created successfully")
                .result(responseDto)
                .build());
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(
            @RequestParam Long personnel_code, @RequestBody EmployeeUpdateRequest updates) {
        Employee updatedEmployee = employeeService.patchEmployee(personnel_code, updates);
        EmployeeResponse responseDto = employeeService.toDto(updatedEmployee);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .message("Employee updated successfully")
                .result(responseDto)
                .build());
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployee(@RequestParam Long personnel_code) {
        Employee employee = employeeService.getEmployeeByPersonelCode(personnel_code);
        EmployeeResponse responseDto = employeeService.toDto(employee);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .message("Employee fetched successfully")
                .result(responseDto)
                .build());
    }

    @GetMapping("/account")
    public ResponseEntity<EmployeeResponse> getEmployeeByAccountId(@RequestParam String id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeByAccountId(id);
        return ResponseEntity.ok(employeeResponse);
    }

    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@RequestParam Long personnel_code) {
        attendanceRepository.deleteByEmployeeCode(personnel_code);
        employeeService.deleteEmployee(personnel_code);
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

    @GetMapping("/department")
    public ResponseEntity<ApiResponse<EWDResponse>> getAllEmployeeInDepartment(
            @RequestParam Long id) {
        EWDResponse ewdResponse = employeeService.getAllEmployeeInDepartment(id);

        return ResponseEntity.ok(ApiResponse.<EWDResponse>builder()
                .code(1000)
                .message("Success")
                .result(ewdResponse)
                .build());
    }

    @PostMapping("/assign-to-department")
    public ResponseEntity<ApiResponse<EWDResponse>> addEmployeeToDepartment(
            @RequestParam Long employeeId,
            @RequestParam Long departmentId) {
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

    @PostMapping("/remove-from-department")
    public ResponseEntity<ApiResponse<Void>> removeEmployeeFromDepartment(
            @RequestParam Long employeeId) {
        try {
            employeeService.removeEmployeeFromDepartment(employeeId);
            return ResponseEntity.ok(ApiResponse.<Void>builder()
                    .message("Employee removed from department successfully")
                    .build());
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.<Void>builder()
                    .code(4040)
                    .message(ex.getMessage())
                    .build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<Void>builder()
                    .code(4000)
                    .message(ex.getMessage())
                    .build());
        }
    }
}
