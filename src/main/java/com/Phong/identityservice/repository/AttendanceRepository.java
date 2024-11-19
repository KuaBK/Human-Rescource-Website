package com.Phong.identityservice.repository;

import com.Phong.identityservice.entity.attendance.Attendance;
import com.Phong.identityservice.entity.personel.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);
    void deleteByEmployeePersonelCode(Long personelCode);
}
