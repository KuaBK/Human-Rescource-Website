package com.Phong.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.identityservice.entity.images.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {}
