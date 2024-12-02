package com.Phong.BackEnd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.Phong.BackEnd.dto.response.Employee.EWDResponse;
import com.Phong.BackEnd.dto.response.Employee.EmplResponse;
import com.Phong.BackEnd.entity.tasks.Tasks;
import com.Phong.BackEnd.repository.*;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.BackEnd.dto.request.Employee.EmployeeCreateRequest;
import com.Phong.BackEnd.dto.request.Employee.EmployeeUpdateRequest;
import com.Phong.BackEnd.dto.response.Employee.EmployeeResponse;
import com.Phong.BackEnd.entity.Account;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.personnel.Position;
import com.Phong.BackEnd.entity.projects.Projects;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            AccountRepository accountRepository,
            DepartmentRepository departmentRepository,
            ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        Account account = accountRepository
                .findById(request.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account không tồn tại"));

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository
                    .findById(request.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));
        }
        if (department != null) {
            department.setEmployeeNumber(department.getEmployeeNumber() + 1);
            departmentRepository.save(department);
        }

        Position position = (request.getPosition() != null) ? request.getPosition() : Position.EMPLOYEE;

        Employee employee = Employee.builder()
                .account(account)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .city(request.getCity())
                .street(request.getStreet())
//                .avatar(request.getAvatar())
                .gender(request.getGender())
                .department(department)
                .position(position)
                .tasksCompleteNumber(0)
                .taskList(new ArrayList<>())
                .projectList(new HashSet<>())
                .project_involved(0)
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .personelCode(savedEmployee.getCode())
                .firstName(savedEmployee.getFirstName())
                .lastName(savedEmployee.getLastName())
                .email(savedEmployee.getEmail())
                .phone(savedEmployee.getPhone())
                .city(savedEmployee.getCity())
                .street(savedEmployee.getStreet())
//                .avatar(savedEmployee.getAvatar())
                .gender(savedEmployee.getGender())
                .departmentName(department != null ? department.getDepartmentName() : null)
                .position(savedEmployee.getPosition())
                .taskList(savedEmployee.getTaskList().stream()
                        .map(Tasks::getTitle)
                        .collect(Collectors.toList()))
                .tasksCompleteNumber(savedEmployee.getTasksCompleteNumber())
                .projectList(savedEmployee.getProjectList().stream()
                        .map(Projects::getProjectName)
                        .collect(Collectors.toList()))
                .projectInvolved(savedEmployee.getProject_involved())
                .build();
    }

    public Employee patchEmployee(Long id, EmployeeUpdateRequest updates) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));

        Department previousDepartment = employee.getDepartment();
        if (updates.getDepartmentId() != null) {
            Department newDepartment = departmentRepository
                    .findById(updates.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));
            if (newDepartment != previousDepartment) {

                if (previousDepartment != null) {
                    previousDepartment.setEmployeeNumber(previousDepartment.getEmployeeNumber() - 1);
                    departmentRepository.save(previousDepartment);
                }
                newDepartment.setEmployeeNumber(newDepartment.getEmployeeNumber() + 1);
                departmentRepository.save(newDepartment);
            }
            employee.setDepartment(newDepartment);
        }

        if (updates.getEmail() != null) employee.setEmail(updates.getEmail());
        if (updates.getFirstName() != null) employee.setFirstName(updates.getFirstName());
        if (updates.getLastName() != null) employee.setLastName(updates.getLastName());
        if (updates.getPhone() != null) employee.setPhone(updates.getPhone());
        if (updates.getCity() != null) employee.setCity(updates.getCity());
        if (updates.getStreet() != null) employee.setStreet(updates.getStreet());
        if (updates.getGender() != null) employee.setGender(updates.getGender());
        if (updates.getPosition() != null) employee.setPosition(updates.getPosition());
        if (updates.getDepartmentId() != null) {
            Department department = departmentRepository
                    .findById(updates.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));
            employee.setDepartment(department);
        }
        if (updates.getTasksCompleteNumber() != null) {
            employee.setTasksCompleteNumber(updates.getTasksCompleteNumber());
        }
        if  (updates.getProjectsInvolved() != null) {
            employee.setProject_involved(updates.getProjectsInvolved());
        }
        if (updates.getTaskList() != null) {
            List<Long> TasksId = updates.getTaskList().stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
            List<Tasks> tasks = new ArrayList<>(taskRepository.findAllById(TasksId));
            employee.setTaskList(tasks);
        }
        if (updates.getProjectList() != null && !updates.getProjectList().isEmpty()) {
            List<Long> projectIds = updates.getProjectList().stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
            Set<Projects> projects = new HashSet<>(projectRepository.findAllById(projectIds));
            employee.setProjectList(projects);
        }
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByPersonelCode(Long personelCode) {
        return employeeRepository
                .findById(personelCode)
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));
    }

    public EmployeeResponse getEmployeeByAccountId(String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account không tồn tại"));
        Employee employee = employeeRepository.findByAccount(account)
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại cho Account này"));
        return toDto(employee);
    }

    @Transactional
    public void deleteEmployee(Long code) {
        Employee employee =  employeeRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with code: " + code));

        Department department = employee.getDepartment();
        if (department != null) {
            department.getEmployees().remove(employee);
            department.setEmployeeNumber(department.getEmployeeNumber() - 1);
            departmentRepository.save(department);
        }
        employeeRepository.deleteById(code);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeResponse toDto(Employee employee) {
        Department department = employee.getDepartment();
        return EmployeeResponse.builder()
                .personelCode(employee.getCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .city(employee.getCity())
                .street(employee.getStreet())
                .avatar(employee.getAvatar())
                .gender(employee.getGender())
                .position(employee.getPosition())
                .departmentName(department != null ? department.getDepartmentName() : null)
                .taskList(employee.getTaskList().stream()
                        .map(Tasks::getTitle)
                        .collect(Collectors.toList()))
                .tasksCompleteNumber(employee.getTasksCompleteNumber())
                .projectList(employee.getProjectList().stream()
                        .map(Projects::getProjectName)
                        .collect(Collectors.toList()))
                .projectInvolved(employee.getProject_involved())
                .build();
    }

    @Transactional
    public EWDResponse addEmployeeToDepartment(Long employeeId, Long departmentId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee không tồn tại"));

        Department newDepartment = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));

        if (employee.getDepartment() != null && employee.getDepartment().equals(newDepartment)) {
            throw new IllegalArgumentException("Employee is already in this department");
        }

        Department currentDepartment = employee.getDepartment();
        if (currentDepartment != null && !currentDepartment.equals(newDepartment)) {
            currentDepartment.getEmployees().remove(employee);
            currentDepartment.setEmployeeNumber(currentDepartment.getEmployeeNumber() - 1);
            departmentRepository.save(currentDepartment);
        }

        employee.setDepartment(newDepartment);
        newDepartment.getEmployees().add(employee);
        newDepartment.setEmployeeNumber(newDepartment.getEmployeeNumber() + 1);

        departmentRepository.save(newDepartment);
        employeeRepository.save(employee);

        return toDTO(newDepartment);
    }

    public EWDResponse getAllEmployeeInDepartment(Long departmentId) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));

        return toDTO(department);
    }

    public EWDResponse toDTO(Department department) {
        List<EmplResponse> employeeResponses = department.getEmployees().stream()
                .map(employee -> new EmplResponse(
                        employee.getCode(),

                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getPhone(),
                        employee.getCity(),
                        employee.getStreet(),
                        employee.getGender(),
                        employee.getEmail()
                ))
                .collect(Collectors.toList());
        return new EWDResponse(department.getDepartmentName(), employeeResponses);
    }
}
