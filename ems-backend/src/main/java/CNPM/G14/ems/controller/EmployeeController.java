package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.AddEmployeeToDepartmentRequest;
import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.EmployeeResponse;
import CNPM.G14.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ApiResponse<EmployeeResponse> addEmployee(@RequestParam int code, int deptId){
        try{
            EmployeeResponse employee = employeeService.addEmployee(code, deptId);
            return ApiResponse.<EmployeeResponse>builder()
                    .EC(0)
                    .EM("Add employee success!")
                    .data(employee)
                    .build();
        } catch (Exception e){
            return ApiResponse.<EmployeeResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<EmployeeResponse>> getALlEmployee(){
        try {
            List<EmployeeResponse> employees = employeeService.getAllEmployee();
            return ApiResponse.<List<EmployeeResponse>>builder()
                    .EC(0)
                    .EM("Fetch employee list success!")
                    .data(employees)
                    .build();
        } catch (Exception e){
            return ApiResponse.<List<EmployeeResponse>>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<EmployeeResponse> getEmployeeById(@RequestParam int code){
        try {
            EmployeeResponse employee = employeeService.getEmployee(code);
            return ApiResponse.<EmployeeResponse>builder()
                    .EC(0)
                    .EM("Fetch employee success")
                    .data(employee)
                    .build();
        } catch (Exception e){
            return ApiResponse.<EmployeeResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/dept")
    public ApiResponse<List<EmployeeResponse>> getEmployeeByDepartment(@RequestParam int deptId){
        try {
            List<EmployeeResponse> employees = employeeService.getEmployeesByDepartmentId(deptId);
            return ApiResponse.<List<EmployeeResponse>>builder()
                    .EC(0)
                    .EM("Fetch employee success")
                    .data(employees)
                    .build();
        } catch (Exception e){
            return ApiResponse.<List<EmployeeResponse>>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PutMapping("/select-dept")
    public ApiResponse<EmployeeResponse> selectEmployee(@RequestBody AddEmployeeToDepartmentRequest request){
        try{
            EmployeeResponse employee = employeeService.addEmployeeToDepartment(request.getEmpCode(), request.getDeptId());
            return ApiResponse.<EmployeeResponse>builder()
                    .EC(0)
                    .EM("Fetch employee success")
                    .data(employee)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<EmployeeResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteEmployee(@RequestParam int code){
        try{
            employeeService.deleteEmployee(code);
            return ApiResponse.<String>builder()
                    .EC(0)
                    .EM("Delete success")
                    .data("Delete employee with code: " + code + " successful!")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data("Delete failed")
                    .build();
        }
    }
}

