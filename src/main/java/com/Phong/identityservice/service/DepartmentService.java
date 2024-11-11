package com.Phong.identityservice.service;

import com.Phong.identityservice.entity.departments.Department;
import com.Phong.identityservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setDepartmentName(departmentDetails.getDepartmentName());
                    department.setEmployeeNumber(departmentDetails.getEmployeeNumber());
                    department.setAddress(departmentDetails.getAddress());
                    department.setManager(departmentDetails.getManager());
                    return departmentRepository.save(department);
                })
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}