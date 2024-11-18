package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.request.EmployeeInProjectRequest;
import CNPM.G14.ems.dto.request.ProjectCreationRequest;
import CNPM.G14.ems.dto.response.EIPResponse;
import CNPM.G14.ems.dto.response.ProjectResponse;
import CNPM.G14.ems.entity.*;
import CNPM.G14.ems.repository.DepartmentRepository;
import CNPM.G14.ems.repository.EmployeeRepository;
import CNPM.G14.ems.repository.ProjectEmployeeRepository;
import CNPM.G14.ems.repository.ProjectRepository;
import CNPM.G14.ems.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectEmployeeRepository projectEmployeeRepository;

    private ProjectResponse toProjectResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setProjectId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setParticipants(project.getParticipants());
        response.setDeptId(project.getDepartment().getId());
        return response;

    }

    @Override
    public ProjectResponse getProject(int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
        return toProjectResponse(project);
    }

    @Override
    public List<ProjectResponse> getProjectsByDeptId(int deptId){
        List<Project> projects = projectRepository.findByDepartmentId(deptId);
        return projects.stream().map(this::toProjectResponse).collect(Collectors.toList());
    }

    @Override
    public ProjectResponse createProject(ProjectCreationRequest request) {
        Department department = departmentRepository.findById(request.getDeptId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + request.getDeptId()));

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setParticipants(0);
        project.setDepartment(department);

        department.getProjects().add(project);

        projectRepository.save(project);

        return toProjectResponse(project);
    }

    @Override
    @Transactional
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::toProjectResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<EIPResponse> findEmployeesInProject(int projectID){
        Project project = projectRepository.findById(projectID)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectID));

        return project.getEmployees().stream()
                .map(e -> new EIPResponse(
                        e.getCode(),
                        e.getPersonnel().getFirstName(),
                        e.getPersonnel().getLastName()
                ))
                .toList();
    }

    @Override
    @Transactional
    public void assignEmployeeToProject(EmployeeInProjectRequest request) {
        Employee employee = employeeRepository.findByCode(request.getEmployeeCode())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with code: " + request.getEmployeeCode()));

        Project project = projectRepository.findById(request.getProjectCode())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + request.getProjectCode()));

        ProjectEmployeeId projectEmployeeId = new ProjectEmployeeId(request.getEmployeeCode(), request.getProjectCode());
        if (projectEmployeeRepository.existsById(projectEmployeeId)) {
            throw new IllegalStateException("Employee is already assigned to this project.");
        }

        EmployeeInProject employeeInProject = new EmployeeInProject();
        employeeInProject.setId(projectEmployeeId);
        employeeInProject.setEmployee(employee);
        employeeInProject.setProject(project);
        projectEmployeeRepository.save(employeeInProject);

//        project.getEmployees().add(employee);
//        employee.getProjects().add(project);

        project.setParticipants(project.getParticipants() + 1);
        employee.setProject_involved(employee.getProject_involved() + 1);

        projectRepository.save(project);
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void removeEmployeeFromProject(EmployeeInProjectRequest request) {

        ProjectEmployeeId projectEmployeeId = new ProjectEmployeeId(request.getEmployeeCode(), request.getProjectCode());

        EmployeeInProject employeeInProject = projectEmployeeRepository.findById(projectEmployeeId)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Relationship between Employee with code: " + request.getEmployeeCode()
                                + " and Project with id: " + request.getProjectCode() + " not found."));

        Project project = employeeInProject.getProject();
        Employee employee = employeeInProject.getEmployee();

        projectEmployeeRepository.delete(employeeInProject);

        project.getEmployees().remove(employee);
        employee.getProjects().remove(project);

        project.setParticipants(project.getParticipants() - 1);
        employee.setProject_involved(employee.getProject_involved() - 1);

        projectRepository.save(project);
        employeeRepository.save(employee);
    }
}
