package com.Phong.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.identityservice.entity.personel.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {}
