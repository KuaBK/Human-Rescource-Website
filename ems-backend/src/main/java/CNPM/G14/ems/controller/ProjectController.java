package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.EmployeeInProjectRequest;
import CNPM.G14.ems.dto.request.ProjectCreationRequest;
import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.EIPResponse;
import CNPM.G14.ems.dto.response.ProjectResponse;
import CNPM.G14.ems.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    public ApiResponse<ProjectResponse> createProject(@RequestBody ProjectCreationRequest request) {
        try{
            ProjectResponse response = projectService.createProject(request);
            return ApiResponse.<ProjectResponse>builder()
                    .EC(0)
                    .EM("Project created successfully!")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<ProjectResponse>builder()
                    .EC(-1)
                    .EM("Failed to create project: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping()
    public ApiResponse<ProjectResponse> getProject(@RequestParam Integer projectId) {
        try{
            ProjectResponse response = projectService.getProject(projectId);
            return ApiResponse.<ProjectResponse>builder()
                    .EC(0)
                    .EM("Project fetched successfully!")
                    .data(response)
                    .build();
        } catch (Exception e){
            return ApiResponse.<ProjectResponse>builder()
                    .EC(-1)
                    .EM("Project not found: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<ProjectResponse>> getAllProjects() {
        try {
            List<ProjectResponse> projects = projectService.getAllProjects();
            return ApiResponse.<List<ProjectResponse>>builder()
                    .EC(0)
                    .EM("Projects fetched successfully!")
                    .data(projects)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<ProjectResponse>>builder()
                    .EC(-1)
                    .EM("Failed to fetch projects: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/project/employees")
    public ApiResponse<List<EIPResponse>> getEmployeesInProject(@RequestParam int projectID) {
        try {
            List<EIPResponse> employees = projectService.findEmployeesInProject(projectID);
            return ApiResponse.<List<EIPResponse>>builder()
                    .EC(0)
                    .EM("Employees retrieved successfully for project ID: " + projectID)
                    .data(employees)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<EIPResponse>>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(Collections.emptyList())
                    .build();
        }
    }

    @GetMapping("/dept")
    public ApiResponse<List<ProjectResponse>> getProjectsByDeptId(@RequestParam int deptId) {
        try {
            List<ProjectResponse> projectResponses = projectService.getProjectsByDeptId(deptId);
            return ApiResponse.<List<ProjectResponse>>builder()
                    .EC(0)
                    .EM("Projects fetched successfully!")
                    .data(projectResponses)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<ProjectResponse>>builder()
                    .EC(-1)
                    .EM("Error fetching projects: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/assign")
    public ApiResponse<Void> assignEmployeeToProject(@RequestBody EmployeeInProjectRequest request) {
        try {
            projectService.assignEmployeeToProject(request);
            return ApiResponse.<Void>builder()
                    .EC(0)
                    .EM("Employee assigned to project successfully!")
                    .data(null)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Void>builder()
                    .EC(-1)
                    .EM("Failed to assign employee to project: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/remove")
    public ApiResponse<Void> removeEmployeeFromProject(@RequestBody EmployeeInProjectRequest request) {
        try {
            projectService.removeEmployeeFromProject(request);
            return ApiResponse.<Void>builder()
                    .EC(0)
                    .EM("Employee removed from project successfully!")
                    .data(null)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Void>builder()
                    .EC(-1)
                    .EM("Failed to remove employee from project: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }
}
