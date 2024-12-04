package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.request.Task.TaskRequest;
import com.Phong.BackEnd.dto.response.Task.TaskResponse;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.File.FileResponse;
import com.Phong.BackEnd.entity.files.File;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.projects.Projects;
import com.Phong.BackEnd.entity.tasks.StatusTasks;
import com.Phong.BackEnd.entity.tasks.Tasks;
import com.Phong.BackEnd.repository.EmployeeRepository;
import com.Phong.BackEnd.repository.FileRepository;
import com.Phong.BackEnd.repository.ProjectRepository;
import com.Phong.BackEnd.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final FileRepository fileRepository;
    private final FileService fileService;

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

        String fileUrl = null;
        String fileName = null;
        if (task.getSubmittedFile() != null) {
            File uploadedFile = fileRepository.findById(task.getSubmittedFile().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Submitted file not found"));
            fileUrl = uploadedFile.getFileUrl();
            fileName = uploadedFile.getFileName();
        }

        TaskResponse response = toDto(task);
        response.setFileUrl(fileUrl);
        response.setFileName(fileName);

        return response;
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

        return tasks.stream().map(task -> {
            String fileUrl = null;
            String fileName = null;

            if(task.getSubmittedFile() != null) {
                File uploadedFile = fileRepository.findById(task.getSubmittedFile().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Submitted file not found"));
                fileUrl = uploadedFile.getFileUrl();
                fileName = uploadedFile.getFileName();
            }

            TaskResponse response = toDto(task);
            response.setFileUrl(fileUrl);
            response.setFileName(fileName);

            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public FileResponse submitTaskWithFile(MultipartFile file, Long taskId, Long personnelId) {
        Tasks task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getEmployee().getCode().equals(personnelId)) {
            throw new RuntimeException("You are not authorized to submit this task");
        }
        FileResponse fileResponse = fileService.uploadFile(file, personnelId);
        File submittedFile = new File();
        submittedFile.setId(fileResponse.getId());
        task.setSubmittedFile(submittedFile);
        task.setStatus(StatusTasks.COMPLETED);
        taskRepository.save(task);
        return fileResponse;
    }

    public ApiResponse<Void> unSubmitTask(Long taskId) {
        Tasks task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));
        if (task.getSubmittedFile() == null) {
            throw new IllegalArgumentException("Task has no submitted file to unsubmit.");
        }
        task.setSubmittedFile(null);
        task.setStatus(StatusTasks.IN_PROGRESS);
        taskRepository.save(task);
        return ApiResponse.<Void>builder()
                .code(2000)
                .message("Task unsubmitted successfully")
                .build();
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
