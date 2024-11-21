package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.request.LeaveApplicatonResquest.LeaveApplicationRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.LeaveAppllication.LeaveApplicationResponse;
import com.Phong.BackEnd.service.LeaveAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveApp")
@RequiredArgsConstructor
public class LeaveAppController {

    private final LeaveAppService leaveAppService;

    @PostMapping
    public ApiResponse<LeaveApplicationResponse> createOnLeaveLetter(@RequestBody LeaveApplicationRequest request) {
        LeaveApplicationResponse response = leaveAppService.createOnLeaveLetter(request);
        return ApiResponse.<LeaveApplicationResponse>builder()
                .code(1000)
                .message("Leave application created successfully")
                .result(response)
                .build();
    }


    @GetMapping("/employee/{employeeId}")
    public ApiResponse<List<LeaveApplicationResponse>> getAllOnLeaveByEmployeeId(@PathVariable Long employeeId) {
        List<LeaveApplicationResponse> responses = leaveAppService.getAllOnLeaveByEmployeeId(employeeId);
        return ApiResponse.<List<LeaveApplicationResponse>>builder()
                .code(1000)
                .message("Leave applications retrieved successfully")
                .result(responses)
                .build();
    }
}
