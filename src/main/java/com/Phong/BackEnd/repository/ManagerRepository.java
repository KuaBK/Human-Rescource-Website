package com.Phong.BackEnd.repository;

import java.util.Optional;

import com.Phong.BackEnd.entity.Account;
import com.Phong.BackEnd.entity.personel.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.personel.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByAccount(Account account);
}
