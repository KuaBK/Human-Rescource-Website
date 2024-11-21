package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.request.Project.ProjectCreateRequest;
import com.Phong.BackEnd.dto.request.Project.EIPRequest;
import com.Phong.BackEnd.dto.response.Project.ProjectResponse;
import com.Phong.BackEnd.dto.response.Project.EIPResponse;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getProject(@PathVariable int projectId) {
        ProjectResponse project = projectService.getProject(projectId);
        return ResponseEntity.ok(ApiResponse.<ProjectResponse>builder()
                .message("Project fetched successfully")
                .result(project)
                .build());
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getProjectsByDepartment(@PathVariable int deptId) {
        List<ProjectResponse> projects = projectService.getProjectsByDeptId(deptId);
        return ResponseEntity.ok(ApiResponse.<List<ProjectResponse>>builder()
                .message("Projects fetched successfully")
                .result(projects)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(@RequestBody @Valid ProjectCreateRequest request) {
        ProjectResponse project = projectService.createProject(request);
        return ResponseEntity.ok(ApiResponse.<ProjectResponse>builder()
                .message("Project created successfully")
                .result(project)
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllProjects() {
        List<ProjectResponse> projects = projectService.getAllProjects();
        return ResponseEntity.ok(ApiResponse.<List<ProjectResponse>>builder()
                .message("All projects fetched successfully")
                .result(projects)
                .build());
    }

    @GetMapping("/employees/{projectId}")
    public ResponseEntity<ApiResponse<List<EIPResponse>>> findEmployeesInProject(@PathVariable int projectId) {
        List<EIPResponse> employees = projectService.findEmployeesInProject(projectId);
        return ResponseEntity.ok(ApiResponse.<List<EIPResponse>>builder()
                .message("Employees in project fetched successfully")
                .result(employees)
                .build());
    }

    @PostMapping("/assign")
    public ResponseEntity<ApiResponse<Void>> assignEmployeeToProject(@RequestBody @Valid EIPRequest request) {
        projectService.assignEmployeeToProject(request);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Employee assigned to project successfully")
                .build());
    }

    @PostMapping("/remove")
    public ResponseEntity<ApiResponse<Void>> removeEmployeeFromProject(@RequestBody @Valid EIPRequest request) {
        projectService.removeEmployeeFromProject(request);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Employee removed from project successfully")
                .build());
    }
}
