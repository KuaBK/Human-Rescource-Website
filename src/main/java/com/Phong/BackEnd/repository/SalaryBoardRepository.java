package com.Phong.BackEnd.repository;

import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.salaryBoard.SalaryBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryBoardRepository extends JpaRepository<SalaryBoard, Long> {
    boolean existsByEmployeeAndMonthAndYear(Employee employee, int month, int year);
    List<SalaryBoard> findAllByEmployee(Employee employee);
    Optional<SalaryBoard> findByEmployeeAndMonthAndYear(Employee employee, Integer month, Integer year);
}