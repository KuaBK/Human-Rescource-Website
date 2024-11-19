package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.OnLeaveRequest;
import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.OnLeaveResponse;
import CNPM.G14.ems.entity.OnLeave;
import CNPM.G14.ems.service.OnLeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/absence")
@AllArgsConstructor
public class OnLeaveController {

    private final OnLeaveService onLeaveService;

    @PostMapping("/create")
    public ApiResponse<OnLeaveResponse> createOnLeaveLetter(@RequestBody OnLeaveRequest request) {
        try {
            OnLeaveResponse response = onLeaveService.createOnLeaveLetter(request);
            return ApiResponse.<OnLeaveResponse>builder()
                    .EC(0)
                    .EM("Create new on leave letter success, owner: " + request.getEmployeeID())
                    .data(response)
                    .build();
        } catch (RuntimeException e) {
            return ApiResponse.<OnLeaveResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/employee")
    public ApiResponse<List<OnLeaveResponse>> getAllOnLeaveByEmployeeId(@RequestParam int employeeId) {
        try {
            List<OnLeaveResponse> responses = onLeaveService.getAllOnLeaveByEmployeeId(employeeId);
            return ApiResponse.<List<OnLeaveResponse>>builder()
                    .EC(0)
                    .EM("Get all on leave letter by employee code: " + employeeId)
                    .data(responses)
                    .build();
        } catch (RuntimeException e) {
            return ApiResponse.<List<OnLeaveResponse>>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }
}
