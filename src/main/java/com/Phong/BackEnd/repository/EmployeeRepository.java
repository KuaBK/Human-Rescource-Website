package com.Phong.BackEnd.repository;

import java.util.List;
import java.util.Optional;

import com.Phong.BackEnd.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Phong.BackEnd.entity.personnel.Employee;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCode(Long Code);
    List<Employee> findByDepartment_DepartmentId(Long departmentId);
    Optional<Employee> findByAccount(Account account);
    Optional<Employee> findByAccountId(String id);

    @Query("SELECT e FROM Employee e")
    List<Employee> findAllEmployees();
}
