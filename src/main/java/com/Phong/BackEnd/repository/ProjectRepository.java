package com.Phong.BackEnd.repository;

import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.projects.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByDepartment(Department department);
}
