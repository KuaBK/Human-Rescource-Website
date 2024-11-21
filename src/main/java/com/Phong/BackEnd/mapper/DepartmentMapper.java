package com.Phong.BackEnd.mapper;

import com.Phong.BackEnd.dto.request.Department.DepartmentCreateRequest;
import com.Phong.BackEnd.dto.request.Department.DepartmentUpdateRequest;
import com.Phong.BackEnd.dto.response.Department.DepartmentResponse;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personel.Manager;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentResponse toDto(Department department) {
        return DepartmentResponse.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .employeeNumber(department.getEmployeeNumber())
                .establishmentDate(department.getEstablishmentDate())
                .address(department.getAddress())
                .managerId(department.getManager() != null ? department.getManager().getPersonelCode() : null)
                .build();
    }

    public Department toEntity(DepartmentCreateRequest request, Manager manager) {
        return Department.builder()
                .departmentName(request.getDepartmentName())
                .address(request.getAddress())
                .manager(manager)
                .build();
    }

    public void updateEntity(Department department, DepartmentUpdateRequest request, Manager manager) {
        if (request.getDepartmentName() != null) {
            department.setDepartmentName(request.getDepartmentName());
        }
        if (request.getAddress() != null) {
            department.setAddress(request.getAddress());
        }
        department.setManager(manager);
    }
}
