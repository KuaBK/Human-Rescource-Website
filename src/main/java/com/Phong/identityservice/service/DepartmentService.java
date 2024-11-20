package com.Phong.identityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.Phong.identityservice.entity.departments.Department;
import com.Phong.identityservice.entity.personel.Manager;
import com.Phong.identityservice.exception.AppException;
import com.Phong.identityservice.exception.ErrorCode;
import com.Phong.identityservice.repository.DepartmentRepository;
import com.Phong.identityservice.repository.ManagerRepository;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, ManagerRepository managerRepository) {
        this.departmentRepository = departmentRepository;
        this.managerRepository = managerRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
    }

    public Department saveDepartment(Department department) {
        if (department.getManager() != null) {
            Manager manager = managerRepository
                    .findById(department.getManager().getPersonelCode())
                    .orElseThrow(() -> new AppException(ErrorCode.MANAGER_NOT_FOUND));
            department.setManager(manager);
        } else {
            department.setManager(null);
        }

        try {
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(
                    ErrorCode.DEPARTMENT_NAME_ALREADY_EXISTS); // Custom error code for duplicate department name
        } catch (Exception e) {
            throw new AppException(ErrorCode.DEPARTMENT_SAVE_ERROR);
        }
    }

    public Department patchDepartment(Long id, Department departmentDetails) {
        Department department =
                departmentRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        if (departmentDetails.getDepartmentName() != null) {
            department.setDepartmentName(departmentDetails.getDepartmentName());
        }
        if (departmentDetails.getAddress() != null) {
            department.setAddress(departmentDetails.getAddress());
        }
        if (departmentDetails.getManager() != null) {
            Manager manager = managerRepository
                    .findById(departmentDetails.getManager().getPersonelCode())
                    .orElseThrow(() -> new AppException(ErrorCode.MANAGER_NOT_FOUND));
            department.setManager(manager);
        } else if (departmentDetails.getManager() == null) {
            department.setManager(null);
        }

        try {
            return departmentRepository.save(department);
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
