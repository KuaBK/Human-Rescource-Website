package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.TaskRequest;
import CNPM.G14.ems.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequestDTO);
    TaskResponse getTask(String id);
    List<TaskResponse> getAllTasks();
    TaskResponse updateTaskStatus(String id, String status);
    TaskResponse assignTaskToEmployee(String taskId, Integer employeeId);
    List<TaskResponse> getTasksByProjectId(int projectId);
    List<TaskResponse> getTasksByEmployeeCode(int employeeId);
}
