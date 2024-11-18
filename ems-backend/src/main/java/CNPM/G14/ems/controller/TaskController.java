package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.TaskRequest;
import CNPM.G14.ems.dto.request.TaskUpdateStatusRequest;
import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.TaskResponse;
import CNPM.G14.ems.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ApiResponse<TaskResponse> createTask(@RequestBody TaskRequest request) {
        try {
            TaskResponse createdTask = taskService.createTask(request);
            return ApiResponse.<TaskResponse>builder()
                    .EC(0) // Success code
                    .EM("Task created successfully")
                    .data(createdTask)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<TaskResponse>builder()
                    .EC(-1) // Error code
                    .EM("Error creating task: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<TaskResponse> getTask(@RequestParam String id) {
        try {
            TaskResponse task = taskService.getTask(id);
            return ApiResponse.<TaskResponse>builder()
                    .EC(0)
                    .EM("Task fetched successfully")
                    .data(task)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<TaskResponse>builder()
                    .EC(-1)
                    .EM("Error fetching task: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<TaskResponse>> getAllTasks() {
        try {
            List<TaskResponse> tasks = taskService.getAllTasks();
            return ApiResponse.<List<TaskResponse>>builder()
                    .EC(0)
                    .EM("Tasks fetched successfully")
                    .data(tasks)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<TaskResponse>>builder()
                    .EC(-1)
                    .EM("Error fetching tasks: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PatchMapping("/update-status")
    public ApiResponse<TaskResponse> updateTaskStatus(@RequestParam String id, @RequestBody TaskUpdateStatusRequest request) {
        try {
            List<String> allowedStatuses = Arrays.asList("IN PROGRESS", "OVERDUE", "COMPLETED");
            if (!allowedStatuses.contains(request.getStatus())) {
                return ApiResponse.<TaskResponse>builder()
                        .EC(-1)
                        .EM("Invalid status value: " + request.getStatus())
                        .data(null)
                        .build();
            }

            TaskResponse updatedTask = taskService.updateTaskStatus(id, request.getStatus());
            return ApiResponse.<TaskResponse>builder()
                    .EC(0)
                    .EM("Task status updated successfully")
                    .data(updatedTask)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<TaskResponse>builder()
                    .EC(-1)
                    .EM("Error updating task status: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/project")
    public ApiResponse<List<TaskResponse>> getTasksByProject(@RequestParam int projectId) {
        try {
            List<TaskResponse> tasks = taskService.getTasksByProjectId(projectId);
            return ApiResponse.<List<TaskResponse>>builder()
                    .EC(0)
                    .EM("Tasks fetched by project ID successfully")
                    .data(tasks)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<TaskResponse>>builder()
                    .EC(-1)
                    .EM("Error fetching tasks by project ID: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/employee")
    public ApiResponse<List<TaskResponse>> getTasksByEmployee(@RequestParam int employeeId) {
        try {
            List<TaskResponse> tasks = taskService.getTasksByEmployeeCode(employeeId);
            return ApiResponse.<List<TaskResponse>>builder()
                    .EC(0)
                    .EM("Tasks fetched by employee ID successfully")
                    .data(tasks)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<List<TaskResponse>>builder()
                    .EC(-1)
                    .EM("Error fetching tasks by employee ID: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PatchMapping("/assign")
    public ApiResponse<TaskResponse> assignTaskToEmployee(
            @RequestParam("id") String id,
            @RequestParam("employeeId") int employeeId) {
        try {
            TaskResponse updatedTask = taskService.assignTaskToEmployee(id, employeeId);
            return ApiResponse.<TaskResponse>builder()
                    .EC(0)
                    .EM("Task assigned to employee successfully")
                    .data(updatedTask)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<TaskResponse>builder()
                    .EC(1)
                    .EM("Error assigning task to employee: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

}
