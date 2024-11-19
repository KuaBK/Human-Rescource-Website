package com.Phong.identityservice.repository;

import com.Phong.identityservice.entity.personel.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Long> {
    Optional<Personel> findByAccountUsername(String username);
}

