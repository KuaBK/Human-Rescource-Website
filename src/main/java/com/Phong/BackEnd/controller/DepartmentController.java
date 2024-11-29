package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.request.Department.DepartmentCreateRequest;
import com.Phong.BackEnd.dto.request.Department.DepartmentUpdateRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Department.DepartmentResponse;
import com.Phong.BackEnd.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartments() {
        List<DepartmentResponse> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(new ApiResponse<>(1000, "Success", departments));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> getDepartmentById(@RequestParam Long id) {
        DepartmentResponse department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(new ApiResponse<>(1000, "Success", department));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DepartmentResponse>> createDepartment(@RequestBody DepartmentCreateRequest request) {
        DepartmentResponse department = departmentService.createDepartment(request);
        return ResponseEntity.ok(new ApiResponse<>(1000, "Department created successfully", department));
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<DepartmentResponse>> patchDepartment(
            @RequestParam Long id, @RequestBody DepartmentUpdateRequest request) {
        DepartmentResponse department = departmentService.patchDepartment(id, request);
        return ResponseEntity.ok(new ApiResponse<>(1000, "Department updated successfully", department));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> deleteDepartment(@RequestParam Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(new ApiResponse<>(1000, "Department deleted successfully", null));
    }
}
