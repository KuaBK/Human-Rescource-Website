package com.Phong.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.departments.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
