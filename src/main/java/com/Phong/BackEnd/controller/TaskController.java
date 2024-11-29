package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.request.Task.TaskRequest;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.File.FileResponse;
import com.Phong.BackEnd.dto.response.Task.TaskResponse;
import com.Phong.BackEnd.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(@RequestBody TaskRequest taskRequestDTO) {
        TaskResponse taskResponse = taskService.createTask(taskRequestDTO);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task created successfully")
                .result(taskResponse)
                .build());
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<TaskResponse>> getTask(@RequestParam Long id) {
        TaskResponse taskResponse = taskService.getTask(id);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task fetched successfully")
                .result(taskResponse)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(ApiResponse.<List<TaskResponse>>builder()
                .message("All tasks fetched successfully")
                .result(tasks)
                .build());
    }

    @PatchMapping("/status-update")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTaskStatus(
            @RequestParam Long id, @RequestParam String status) {
        TaskResponse taskResponse = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task status updated successfully")
                .result(taskResponse)
                .build());
    }

    @PatchMapping("/assign")
    public ResponseEntity<ApiResponse<TaskResponse>> assignTaskToEmployee(
            @RequestParam Long taskId, @RequestParam Long employeeId) {
        TaskResponse taskResponse = taskService.assignTaskToEmployee(taskId, employeeId);
        return ResponseEntity.ok(ApiResponse.<TaskResponse>builder()
                .message("Task assigned successfully")
                .result(taskResponse)
                .build());
    }

    @GetMapping("/project")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTasksByProjectId(@RequestParam int id) {
        List<TaskResponse> tasks = taskService.getTasksByProjectId(id);
        return ResponseEntity.ok(ApiResponse.<List<TaskResponse>>builder()
                .message("Tasks fetched successfully by project")
                .result(tasks)
                .build());
    }

    @GetMapping("/employee")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getTasksByEmployeeCode(@RequestParam Long code) {
        List<TaskResponse> tasks = taskService.getTasksByEmployeeCode(code);
        return ResponseEntity.ok(ApiResponse.<List<TaskResponse>>builder()
                .message("Tasks fetched successfully by employee")
                .result(tasks)
                .build());
    }

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<FileResponse>> submitTaskWithFile(
            @RequestParam Long taskId,
            @RequestParam MultipartFile file,
            @RequestParam Long personnelId) {
        FileResponse fileResponse = taskService.submitTaskWithFile(file, taskId, personnelId);

        ApiResponse<FileResponse> response = ApiResponse.<FileResponse>builder()
                .message("Task submitted successfully")
                .result(fileResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/unsubmit")
    public ResponseEntity<ApiResponse<Void>> unSubmitTask(@RequestParam Long taskId) {
        ApiResponse<Void> response = taskService.unSubmitTask(taskId);
        return ResponseEntity.ok(response);
    }
}
