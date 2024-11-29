package com.Phong.BackEnd.repository;

import com.Phong.BackEnd.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.personnel.Manager;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByAccount(Account account);
}
