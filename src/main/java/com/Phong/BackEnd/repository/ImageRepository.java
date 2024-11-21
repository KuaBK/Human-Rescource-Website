package com.Phong.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.images.Image;
import com.Phong.BackEnd.entity.personel.Personel;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteByUploadedBy(Personel personel);
}
