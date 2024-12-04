package com.Phong.BackEnd.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.attendance.Attendance;
import com.Phong.BackEnd.entity.personnel.Employee;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);
    void deleteByEmployeeCode(Long Code);
    List<Attendance> findByEmployee(Employee employee);
    List<Attendance> findByDate(LocalDate date);

    @Query("SELECT a FROM Attendance a WHERE MONTH(a.date) = :month AND YEAR(a.date) = :year")
    List<Attendance> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
