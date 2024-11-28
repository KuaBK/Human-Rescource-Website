package com.Phong.BackEnd.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.Phong.BackEnd.dto.request.Manager.MTDResponse;
import com.Phong.BackEnd.dto.response.Employee.EmployeeResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Phong.BackEnd.dto.request.Manager.ManagerCreateRequest;
import com.Phong.BackEnd.dto.request.Manager.ManagerUpdateRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Manager.ManagerResponse;
import com.Phong.BackEnd.entity.personel.Manager;
import com.Phong.BackEnd.repository.AttendanceRepository;
import com.Phong.BackEnd.service.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public ManagerController(ManagerService managerService, AttendanceRepository attendanceRepository) {
        this.managerService = managerService;
        this.attendanceRepository = attendanceRepository;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ManagerResponse>> createManager(
            @RequestBody @Valid ManagerCreateRequest request) {

        ManagerResponse responseDto = managerService.createManager(request);

        return ResponseEntity.ok(ApiResponse.<ManagerResponse>builder()
                .message("Manager created successfully")
                .result(responseDto)
                .build());
    }

    @GetMapping("/{personelCode}")
    public ResponseEntity<ApiResponse<ManagerResponse>> getManager(@PathVariable Long personelCode) {
        Manager manager = managerService.getManagerByPersonelCode(personelCode);
        ManagerResponse responseDto = managerService.toDto(manager);
        return ResponseEntity.ok(ApiResponse.<ManagerResponse>builder()
                .message("Manager fetched successfully")
                .result(responseDto)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ManagerResponse>>> getAllManagers() {
        List<Manager> managers = managerService.getAllManagers();
        List<ManagerResponse> responseDtos =
                managers.stream().map(managerService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.<List<ManagerResponse>>builder()
                .message("All managers fetched successfully")
                .result(responseDtos)
                .build());
    }

    @PatchMapping("/{personelCode}")
    public ResponseEntity<ApiResponse<ManagerResponse>> updateEmployee(
            @PathVariable Long personelCode, @RequestBody ManagerUpdateRequest updates) {
        Manager updatedManager = managerService.patchManager(personelCode, updates);
        ManagerResponse responseDto = managerService.toDto(updatedManager);
        return ResponseEntity.ok(ApiResponse.<ManagerResponse>builder()
                .message("Manager updated successfully")
                .result(responseDto)
                .build());
    }

    @Transactional
    @DeleteMapping("/{personelCode}")
    public ResponseEntity<ApiResponse<Void>> deleteManager(@PathVariable Long personelCode) {
        attendanceRepository.deleteByEmployeeCode(personelCode);
        managerService.deleteEmployee(personelCode);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Manager deleted successfully")
                .build());
    }

    @PostMapping("/assign")
    public ApiResponse<MTDResponse> assignManagerToDept(@RequestParam Long code, Long deptId){
        try{
            MTDResponse response = managerService.assignManagerToDept(code, deptId);
            return ApiResponse.<MTDResponse>builder()
                    .code(1000)
                    .message("Assign manager code: " + code + " into department id: " + deptId)
                    .result(response)
                    .build();
        } catch (Exception e){
            return ApiResponse.<MTDResponse>builder()
                    .code(-1)
                    .message(e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @PostMapping("/remove")
    public ApiResponse<String> removeManagerFromDept(@RequestParam Long deptId) {
        try {
            managerService.removeManagerFromDepartment(deptId);
            return ApiResponse.<String>builder()
                    .code(1000)
                    .message("Manager removed from department id: " + deptId)
                    .result("Manager has been successfully removed from the department.")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .code(-1)
                    .message(e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<ManagerResponse> getManagerByAccountId(@PathVariable String accountId) {
        ManagerResponse managerResponse = managerService.getManagerByAccountId(accountId);
        return ResponseEntity.ok(managerResponse);
    }
}
