package com.Phong.BackEnd.repository;

import com.Phong.BackEnd.entity.leaveApplication.LeaveApplication;
import com.Phong.BackEnd.entity.personel.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
    List<LeaveApplication> findByEmployee(Employee employee);
}
