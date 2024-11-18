//package CNPM.G14.ems.controller;
//
//import CNPM.G14.ems.dto.request.SalaryBoardRequest;
//import CNPM.G14.ems.dto.response.ApiResponse;
//import CNPM.G14.ems.dto.response.SalaryBoardResponse;
//import CNPM.G14.ems.entity.SalaryBoard;
//import CNPM.G14.ems.service.SalaryBoardService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/salary")
//@AllArgsConstructor
//public class SalaryBoardController {
//
//    private final SalaryBoardService salaryBoardService;
//
//    @PostMapping
//    public ApiResponse<SalaryBoardResponse> createSalaryBoard(@RequestBody SalaryBoardRequest dto) {
//        try {
//            SalaryBoardResponse response = salaryBoardService.createSalaryBoard(dto);
//            return ApiResponse.<SalaryBoardResponse>builder()
//                    .EC(0) // Success code
//                    .EM("Salary board created successfully")
//                    .data(response)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<SalaryBoardResponse>builder()
//                    .EC(-1) // Error code
//                    .EM("Error creating salary board: " + e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//
//    @GetMapping
//    public ApiResponse<SalaryBoardResponse> getSalaryBoard(@RequestParam Long id) {
//        try {
//            SalaryBoardResponse response = salaryBoardService.getSalaryBoard(id);
//            return ApiResponse.<SalaryBoardResponse>builder()
//                    .EC(0) // Success code
//                    .EM("Salary board fetched successfully")
//                    .data(response)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<SalaryBoardResponse>builder()
//                    .EC(-1) // Error code
//                    .EM("Error fetching salary board: " + e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//    @PatchMapping("/edit")
//    public ApiResponse<SalaryBoardResponse> updateSalaryBoard(
//            @RequestParam Long id,
//            @RequestBody SalaryBoard updatedFields) {
//        try {
//            SalaryBoardResponse response = salaryBoardService.updateSalaryBoard(id, updatedFields);
//            return ApiResponse.<SalaryBoardResponse>builder()
//                    .EC(0) // Success code
//                    .EM("Salary board updated successfully")
//                    .data(response)
//                    .build();
//        } catch (Exception e) {
//            return ApiResponse.<SalaryBoardResponse>builder()
//                    .EC(-1) // Error code
//                    .EM("Error updating salary board: " + e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//}
