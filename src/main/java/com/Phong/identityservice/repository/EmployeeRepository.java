package com.Phong.identityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Phong.identityservice.entity.personel.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByPersonelCode(Long personelCode);
}
