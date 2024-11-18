package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.ManagerResponse;
import CNPM.G14.ems.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private final ManagerService managerService;

    @PostMapping("/add")
    public ApiResponse<Integer> addEmployee(@RequestParam int code){
        try{
            Integer response = managerService.addManager(code);
            return ApiResponse.<Integer>builder()
                    .EC(0)
                    .EM("Add manager code: " + code)
                    .data(response)
                    .build();
        } catch (Exception e){
            return ApiResponse.<Integer>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/assign")
    public ApiResponse<ManagerResponse> assignManagerToDept(@RequestParam int code, int deptId){
        try{
            ManagerResponse response = managerService.assignManagerToDept(code, deptId);
            return ApiResponse.<ManagerResponse>builder()
                    .EC(0)
                    .EM("Assign manager code: " + code + " into department id: " + deptId)
                    .data(response)
                    .build();
        } catch (Exception e){
            return ApiResponse.<ManagerResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/remove")
    public ApiResponse<String> removeManagerFromDept(@RequestParam int deptId) {
        try {
            managerService.removeManagerFromDepartment(deptId);
            return ApiResponse.<String>builder()
                    .EC(0)
                    .EM("Manager removed from department id: " + deptId)
                    .data("Manager has been successfully removed from the department.")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<ManagerResponse> getManager(int code){
        try {
            ManagerResponse response = managerService.getManager(code);
            return ApiResponse.<ManagerResponse>builder()
                    .EC(0)
                    .EM("Fetch manager success")
                    .data(response)
                    .build();
        } catch (Exception e){
            return ApiResponse.<ManagerResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<ManagerResponse>> getALlManger(){
        try {
            List<ManagerResponse> managers = managerService.getAllManagers();
            return ApiResponse.<List<ManagerResponse>>builder()
                    .EC(0)
                    .EM("Fetch manager list success!")
                    .data(managers)
                    .build();
        } catch (Exception e){
            return ApiResponse.<List<ManagerResponse>>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

}
