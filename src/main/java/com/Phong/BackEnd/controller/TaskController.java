package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.request.Task.TaskRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Task.TaskResponse;
import com.Phong.BackEnd.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(@RequestBody TaskRequest taskRequestDTO) {
        TaskResponse taskResponse = taskService.createTask(taskRequestDTO);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task created successfully")
                .result(taskResponse)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTask(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getTask(id);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task fetched successfully")
                .result(taskResponse)
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(ApiResponse.<List<TaskResponse>>builder()
                .message("All tasks fetched successfully")
                .result(tasks)
                .build());
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTaskStatus(
            @PathVariable Long id, @RequestParam String status) {
        TaskResponse taskResponse = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task status updated successfully")
                .result(taskResponse)
                .build());
    }

    @PatchMapping("/{taskId}/assign/{employeeId}")
    public ResponseEntity<ApiResponse<TaskResponse>> assignTaskToEmployee(
            @PathVariable Long taskId, @PathVariable Long employeeId) {
        TaskResponse taskResponse = taskService.assignTaskToEmployee(taskId, employeeId);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task assigned successfully")
                .result(taskResponse)
                .build());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTasksByProjectId(@PathVariable int projectId) {
        List<TaskResponse> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(ApiResponse.<List<TaskResponse>>builder()
                .message("Tasks fetched successfully by project")
                .result(tasks)
                .build());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTasksByEmployeeCode(@PathVariable int employeeId) {
        List<TaskResponse> tasks = taskService.getTasksByEmployeeCode(employeeId);
        return ResponseEntity.ok(ApiResponse.<List<TaskResponse>>builder()
                .message("Tasks fetched successfully by employee")
                .result(tasks)
                .build());
    }
}
