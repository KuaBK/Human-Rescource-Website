package com.Phong.BackEnd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.personnel.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Optional<Personnel> findByAccountUsername(String username);
    Optional<Personnel> findByAccountId(String id);
}
