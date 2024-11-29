package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.request.Department.*;
import com.Phong.BackEnd.dto.response.Department.DepartmentResponse;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personnel.Manager;
import com.Phong.BackEnd.exception.AppException;
import com.Phong.BackEnd.exception.ErrorCode;
import com.Phong.BackEnd.mapper.DepartmentMapper;
import com.Phong.BackEnd.repository.DepartmentRepository;
import com.Phong.BackEnd.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ManagerRepository managerRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, ManagerRepository managerRepository,
                             DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.managerRepository = managerRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        return departmentMapper.toDto(department);
    }

    public DepartmentResponse createDepartment(DepartmentCreateRequest request) {
        Manager manager = null;
        if (request.getManagerId() != null) {
            manager = managerRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new AppException(ErrorCode.MANAGER_NOT_FOUND));
        }

        Department department = departmentMapper.toEntity(request, manager);
        department.setEmployeeNumber(request.getEmployeeNumber());

        Department savedDepartment = departmentRepository.save(department);

        if (manager != null) {
            manager.setDepartment(savedDepartment);
            managerRepository.save(manager);
        }

        try {
            return departmentMapper.toDto(savedDepartment);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.DEPARTMENT_NAME_ALREADY_EXISTS);
        }
    }


    public DepartmentResponse patchDepartment(Long id, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        Manager manager = null;
        if (request.getManagerId() != null) {
            manager = managerRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new AppException(ErrorCode.MANAGER_NOT_FOUND));
            manager.setDepartment(department);
            managerRepository.save(manager);
        }
        if (request.getEmployeeNumber() != null) {
            department.setEmployeeNumber(request.getEmployeeNumber());
        }

        departmentMapper.updateEntity(department, request, manager);

        try {
            Department updated = departmentRepository.save(department);
            return departmentMapper.toDto(updated);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DEPARTMENT_SAVE_ERROR);
        }
    }

    public void deleteDepartment(Long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DEPARTMENT_DELETE_ERROR);
        }
    }
}
