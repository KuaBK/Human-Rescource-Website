package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.request.Task.TaskRequest;
import com.Phong.BackEnd.dto.response.Task.TaskResponse;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.projects.Projects;
import com.Phong.BackEnd.entity.tasks.StatusTasks;
import com.Phong.BackEnd.entity.tasks.Tasks;
import com.Phong.BackEnd.repository.EmployeeRepository;
import com.Phong.BackEnd.repository.ProjectRepository;
import com.Phong.BackEnd.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public TaskResponse createTask(TaskRequest taskRequestDTO) {
        Projects project = projectRepository.findById(taskRequestDTO.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Dự án không tồn tại"));

        Employee employee = null;
        if (taskRequestDTO.getEmployeeId() != null) {
            employee = employeeRepository.findById(taskRequestDTO.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Nhân viên không tồn tại"));
        }

        StatusTasks status = StatusTasks.IN_PROGRESS;
        Tasks task = Tasks.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .due(taskRequestDTO.getDue())
                .status(status)
                .project(project)
                .employee(employee)
                .build();

        Tasks savedTask = taskRepository.save(task);

        return toDto(savedTask);
    }

    public TaskResponse getTask(Long id) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task không tồn tại"));
        return toDto(task);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public TaskResponse updateTaskStatus(Long id, String status) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task không tồn tại"));

        task.setStatus(StatusTasks.valueOf(status));
        if ("COMPLETED".equals(status) && task.getEmployee() != null) {
            Employee employee = task.getEmployee();
            employee.setTasksCompleteNumber(employee.getTasksCompleteNumber() + 1);
            employeeRepository.save(employee);
        }
        taskRepository.save(task);

        return toDto(task);
    }

    public TaskResponse assignTaskToEmployee(Long taskId, Long employeeId) {
        Tasks task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task không tồn tại"));

        Employee employee = employeeRepository.findByCode(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Nhân viên không tồn tại"));

        task.setEmployee(employee);
        taskRepository.save(task);

        return toDto(task);
    }

    public List<TaskResponse> getTasksByProjectId(int projectId) {
        List<Tasks> tasks = taskRepository.findByProjectProjectId(projectId);
        return tasks.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<TaskResponse> getTasksByEmployeeCode(Long employeeId) {
        List<Tasks> tasks = taskRepository.findByEmployeeCode(employeeId);
        return tasks.stream().map(this::toDto).collect(Collectors.toList());
    }
    private TaskResponse toDto(Tasks task) {
        return TaskResponse.builder()
                .tasksId(task.getTasksId())
                .title(task.getTitle())
                .description(task.getDescription())
                .due(task.getDue())
                .status(task.getStatus().name())
                .projectName(task.getProject() != null ? task.getProject().getProjectName() : null)
                .employeeName(task.getEmployee() != null
                        ? task.getEmployee().getFirstName() + " " + task.getEmployee().getLastName()
                        : null)
                .build();
    }
}
