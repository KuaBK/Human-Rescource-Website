package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.DepartmentRequest;
import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.DepartmentResponse;
import CNPM.G14.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ApiResponse<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest request) {
        try {
            DepartmentResponse departmentResponse = departmentService.createDepartment(request);
            return ApiResponse.<DepartmentResponse>builder()
                    .EC(0)
                    .EM("Department created successfully!")
                    .data(departmentResponse)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<DepartmentResponse>builder()
                    .EC(-1)
                    .EM("Error creating department: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<DepartmentResponse> getDepartmentById(@RequestParam int deptId){
        try {
            DepartmentResponse departmentResponse = departmentService.getDepartment(deptId);
            return ApiResponse.<DepartmentResponse>builder()
                    .EC(0)
                    .EM("Department fetched successfully!")
                    .data(departmentResponse)
                    .build();

        } catch (Exception e) {
            return ApiResponse.<DepartmentResponse>builder()
                    .EC(-1)
                    .EM("Department not found: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<DepartmentResponse>> getAllDepartment(){
        try{
            List<DepartmentResponse> departments = departmentService.getAllDepartments();
            return ApiResponse.<List<DepartmentResponse>>builder()
                    .EC(0)
                    .EM("Departments fetched successfully!")
                    .data(departments)
                    .build();
        } catch (Exception e){
            return ApiResponse.<List<DepartmentResponse>>builder()
                    .EC(-1)
                    .EM("Error fetching departments: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteDepartment(@RequestParam int deptId){
        try{
            departmentService.deleteDepartment(deptId);
            return ApiResponse.<String>builder()
                    .EC(0)
                    .EM("Department deleted successfully!")
                    .data("Department ID " + deptId)
                    .build();
        } catch (Exception e){
            return ApiResponse.<String>builder()
                    .EC(-1)
                    .EM("Error deleting department: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }
}

