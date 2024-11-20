package com.Phong.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.identityservice.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {}
