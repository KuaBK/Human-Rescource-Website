package com.Phong.identityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.identityservice.entity.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, String> {
    boolean existsByUsername(String username);

    Optional<Account> findByUsername(String username);
}
