package com.Phong.BackEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.personnel.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Optional<Personnel> findByAccountUsername(String username);

    @Query("SELECT p FROM Personnel p WHERE TYPE(p) = Employee")
    List<Personnel> findAllEmployees();
}
