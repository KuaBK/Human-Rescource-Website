package com.Phong.identityservice.repository;

import com.Phong.identityservice.entity.personel.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.identityservice.entity.images.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteByUploadedBy(Personel personel);
}
