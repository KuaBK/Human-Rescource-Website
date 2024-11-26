package com.Phong.BackEnd.repository;

import com.Phong.BackEnd.entity.tasks.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByProjectProjectId(int projectId);

    List<Tasks> findByEmployeeCode(Long Code);
}
