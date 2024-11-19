package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.SalaryBoardRequest;
import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.SalaryBoardResponse;
import CNPM.G14.ems.entity.SalaryBoard;
import CNPM.G14.ems.service.SalaryBoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
                    .EC(0) // Success code
                    .EM("Salary board created successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .EC(-1) // Error code
                    .EM("Error creating salary board: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<SalaryBoardResponse> getSalaryBoard(@RequestParam Long id) {
        try {
            SalaryBoardResponse response = salaryBoardService.getSalaryBoard(id);
            return ApiResponse.<SalaryBoardResponse>builder()
                    .EC(0) // Success code
                    .EM("Salary board fetched successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .EC(-1) // Error code
                    .EM("Error fetching salary board: " + e.getMessage())
                    .data(null)
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
                    .EC(0)
                    .EM("Salary board updated successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .EC(-1)
                    .EM("Error updating salary board: " + e.getMessage())
                    .data(null)
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
                    .EC(0)
                    .EM("Pay rates updated successfully")
                    .data(null)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .EC(-1)
                    .EM("Error updating pay rates: " + e.getMessage())
                    .data(null)
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
                    .EC(0)
                    .EM("Bonus and penalty updated successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .EC(-1)
                    .EM("Error updating bonus and penalty: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/employee")
    public ApiResponse<List<SalaryBoardResponse>> getAllSalaryBoardsForEmployee(@RequestParam int employeeCode) {
        try {
            List<SalaryBoardResponse> responses = salaryBoardService.getAllSalaryBoardsByEmployee(employeeCode);
            return ApiResponse.<List<SalaryBoardResponse>>builder()
                    .EC(0)
                    .EM("Salary boards fetched successfully")
                    .data(responses)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<SalaryBoardResponse>>builder()
                    .EC(-1)
                    .EM("Error fetching salary boards: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }


    @GetMapping("/employee/month-year")
    public ApiResponse<SalaryBoardResponse> getSalaryBoardByEmployeeAndDate(
            @RequestParam int employeeCode,
            @RequestParam int month,
            @RequestParam int year) {
        try {
            SalaryBoardResponse response = salaryBoardService.getSalaryBoardByEmployeeAndDate(employeeCode, month, year);
            return ApiResponse.<SalaryBoardResponse>builder()
                    .EC(0)
                    .EM("Salary board fetched successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<SalaryBoardResponse>builder()
                    .EC(-1)
                    .EM("Error fetching salary board: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

}
