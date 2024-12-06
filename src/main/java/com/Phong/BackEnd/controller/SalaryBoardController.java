package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.request.SalaryBoard.SalaryBoardRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.SalaryBoard.SalaryBoardResponse;
import com.Phong.BackEnd.entity.salaryBoard.SalaryBoard;
import com.Phong.BackEnd.service.SalaryBoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/salary")
@AllArgsConstructor
public class SalaryBoardController {

    private final SalaryBoardService salaryBoardService;

    @PostMapping("/create")
    public ApiResponse<SalaryBoardResponse> createSalaryBoard(@RequestBody SalaryBoardRequest request) {
        try {
            SalaryBoardResponse response = salaryBoardService.createSalaryBoard(request);
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(1000)
                    .message("Salary board created successfully")
                    .result(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(-1)
                    .message("Error creating salary board: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<SalaryBoardResponse> getSalaryBoard(@RequestParam Long id) {
        try {
            SalaryBoardResponse response = salaryBoardService.getSalaryBoard(id);
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(1000)
                    .message("Salary board fetched successfully")
                    .result(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(-1) // Error code
                    .message("Error fetching salary board: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<SalaryBoardResponse>> getAllSalaryBoard(){
        try{
            List<SalaryBoardResponse> responses = salaryBoardService.getAllSalaryBoards();
            return ApiResponse.<List<SalaryBoardResponse>>builder()
                    .code(1000)
                    .message("All salary board successfully")
                    .result(responses)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<SalaryBoardResponse>>builder()
                    .code(-1)
                    .message("Error fetch all salary board: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @PatchMapping("/edit")
    public ApiResponse<SalaryBoardResponse> updateSalaryBoard(
            @RequestParam Long id,
            @RequestBody SalaryBoard updatedFields) {
        try {
            SalaryBoardResponse response = salaryBoardService.updateSalaryBoard(id, updatedFields);
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(1000)
                    .message("Salary board updated successfully")
                    .result(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(-1)
                    .message("Error updating salary board: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @PatchMapping("/payrate-edit")
    public ApiResponse<String> updatePayRate(
            @RequestParam double fullWorkPay,
            @RequestParam double halfWorkPay) {
        try {
            salaryBoardService.updatePayRate(fullWorkPay, halfWorkPay);
            return ApiResponse.<String>builder()
                    .code(1000)
                    .message("Pay rates updated successfully")
                    .result(null)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .code(-1)
                    .message("Error updating pay rates: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @PatchMapping("/bonus-penalty")
    public ApiResponse<SalaryBoardResponse> updateBonusAndPenalty(
            @RequestParam Long id,
            @RequestParam double bonus,
            @RequestParam double penalty) {
        try {
            SalaryBoardResponse response = salaryBoardService.updateBonusAndPenalty(id, bonus, penalty);
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(1000)
                    .message("Bonus and penalty updated successfully")
                    .result(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(-1)
                    .message("Error updating bonus and penalty: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping("/employee")
    public ApiResponse<List<SalaryBoardResponse>> getAllSalaryBoardsForEmployee(@RequestParam Long employeeCode) {
        try {
            List<SalaryBoardResponse> responses = salaryBoardService.getAllSalaryBoardsByEmployee(employeeCode);
            return ApiResponse.<List<SalaryBoardResponse>>builder()
                    .code(1000)
                    .message("Salary boards fetched successfully")
                    .result(responses)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<SalaryBoardResponse>>builder()
                    .code(-1)
                    .message("Error fetching salary boards: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }


    @GetMapping("/employee/month-year")
    public ApiResponse<SalaryBoardResponse> getSalaryBoardByEmployeeAndDate(
            @RequestParam Long employeeCode,
            @RequestParam int month,
            @RequestParam int year) {
        try {
            salaryBoardService.synchronizeSalaryBoard(employeeCode, month, year);
            SalaryBoardResponse response = salaryBoardService.getSalaryBoardByEmployeeAndDate(employeeCode, month, year);
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(1000)
                    .message("Salary board fetched successfully")
                    .result(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .code(-1)
                    .message("Error fetching salary board: " + e.getMessage())
                    .result(null)
                    .build();
        }
    }


}
