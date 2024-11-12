package com.Phong.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.identityservice.entity.departments.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
