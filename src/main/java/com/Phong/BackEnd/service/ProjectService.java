package com.Phong.BackEnd.service;

import java.util.List;
import java.util.stream.Collectors;

import com.Phong.BackEnd.entity.projects.ProjectStatus;
import com.Phong.BackEnd.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.BackEnd.dto.request.Project.ProjectCreateRequest;
import com.Phong.BackEnd.dto.request.Project.EIPRequest;
import com.Phong.BackEnd.dto.response.Project.ProjectResponse;
import com.Phong.BackEnd.dto.response.Project.EIPResponse;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.projects.Projects;
import com.Phong.BackEnd.repository.DepartmentRepository;
import com.Phong.BackEnd.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProjectService(
            ProjectRepository projectRepository,
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public ProjectResponse getProject(int projectId) {
        Projects project = projectRepository
                .findById((long) projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project không tồn tại"));

        return toDto(project);
    }

    public List<ProjectResponse> getProjectsByDeptId(int deptId) {
        Department department = departmentRepository
                .findById((long) deptId)
                .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));

        List<Projects> projects = projectRepository.findByDepartment(department);

        return projects.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProjectResponse createProject(ProjectCreateRequest request) {
        Department department = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));

//        ProjectStatus status = ProjectStatus.IN_PROGRESS;
        Projects project = Projects.builder()
                .projectName(request.getProjectName())
                .projectDescription(request.getProjectDescription())
//                .projectStatus(status)
                .department(department)
                .participants(request.getParticipants())
                .build();

        Projects savedProject = projectRepository.save(project);

        return toDto(savedProject);
    }

    public List<ProjectResponse> getAllProjects() {
        List<Projects> projects = projectRepository.findAll();

        return projects.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<EIPResponse> findEmployeesInProject(int projectId) {
        Projects project = projectRepository
                .findById((long) projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project không tồn tại"));

        return project.getEmployees().stream()
                .map(employee -> EIPResponse.builder()
                        .employeeId(employee.getCode())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public void assignEmployeeToProject(EIPRequest request) {
        Projects project = projectRepository
                .findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project không tồn tại"));

        Employee employee = employeeRepository
                .findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));

        project.getEmployees().add(employee);
        employee.getProjectList().add(project);
        employee.setProject_involved(employee.getProject_involved() + 1);

        employeeRepository.save(employee);
        project.setParticipants(project.getParticipants() + 1);
        projectRepository.save(project);
    }

    public void removeEmployeeFromProject(EIPRequest request) {
        Projects project = projectRepository
                .findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project không tồn tại"));

        Employee employee = employeeRepository
                .findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));

        if (!project.getEmployees().contains(employee)) {
            throw new IllegalArgumentException("Nhân viên không thuộc dự án này, không thể xóa.");
        }

        project.getEmployees().remove(employee);
        project.setParticipants(project.getParticipants() - 1);
        employee.getProjectList().remove(project);

        employeeRepository.save(employee);
        projectRepository.save(project);
    }


    private ProjectResponse toDto(Projects project) {
        return ProjectResponse.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .projectDescription(project.getProjectDescription())
//                .projectStatus(project.getProjectStatus().name())
                .departmentName(project.getDepartment() != null ? project.getDepartment().getDepartmentName() : null)
                .participants(project.getParticipants())
                .build();
    }
}
