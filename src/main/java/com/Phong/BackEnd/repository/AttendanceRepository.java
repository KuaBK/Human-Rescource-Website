package com.Phong.BackEnd.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.attendance.Attendance;
import com.Phong.BackEnd.entity.personel.Employee;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);

    void deleteByEmployeePersonelCode(Long personelCode);
}
