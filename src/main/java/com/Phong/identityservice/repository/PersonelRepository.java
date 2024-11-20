package com.Phong.identityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.identityservice.entity.personel.Personel;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Long> {
    Optional<Personel> findByAccountUsername(String username);
}
