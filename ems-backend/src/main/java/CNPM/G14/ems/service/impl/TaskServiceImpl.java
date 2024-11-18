package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.request.TaskRequest;
import CNPM.G14.ems.dto.response.TaskResponse;
import CNPM.G14.ems.exception.ResourceNotFoundException;
import CNPM.G14.ems.repository.EmployeeRepository;
import CNPM.G14.ems.repository.ProjectRepository;
import CNPM.G14.ems.repository.TaskRepository;
import CNPM.G14.ems.service.TaskService;
import lombok.*;
import org.springframework.stereotype.Service;

import CNPM.G14.ems.entity.Project;
import CNPM.G14.ems.entity.Employee;
import CNPM.G14.ems.entity.Task;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    private TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getDescription(),
                task.getDue(),
                task.getStatus(),
                task.getProject().getId(),
                task.getEmployee().getCode()
        );
    }

    private void validateTaskRequest(TaskRequest taskRequest) {
        if (taskRequest.getDescription() == null || taskRequest.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }
        if (taskRequest.getDue() == null || taskRequest.getDue().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Task due date cannot be null or in the past.");
        }
    }

    @Override
    public TaskResponse createTask(TaskRequest request) {

        validateTaskRequest(request);

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + request.getProjectId()));
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with code: " + request.getEmployeeId()));

        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setDue(request.getDue());
        task.setStatus("IN PROGRESS");
        task.setProject(project);
        task.setEmployee(employee);

        return toTaskResponse(taskRepository.save(task));
    }

    @Override
    @Transactional
    public TaskResponse getTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
        return toTaskResponse(task);
    }

    @Override
    @Transactional
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskResponse updateTaskStatus(String id, String status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));

        task.setStatus(status);
        return toTaskResponse(taskRepository.save(task));
    }

    @Override
    @Transactional
    public TaskResponse assignTaskToEmployee(String taskId, Integer employeeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

        Employee employee = employeeRepository.findByCode(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        employee.getTasks().add(task);
        task.setEmployee(employee);

        return toTaskResponse(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> getTasksByProjectId(int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        return taskRepository.findByProject(project).stream()
                .map(this::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getTasksByEmployeeCode(int employeeCode) {
        Employee employee = employeeRepository.findByCode(employeeCode)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeCode));

        return taskRepository.findByEmployee(employee).stream()
                .map(this::toTaskResponse)
                .collect(Collectors.toList());
    }
}
