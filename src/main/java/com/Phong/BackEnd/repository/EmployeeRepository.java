package com.Phong.BackEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Phong.BackEnd.entity.personel.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCode(Long Code);
    List<Employee> findByDepartment_DepartmentId(Long departmentId);
}
