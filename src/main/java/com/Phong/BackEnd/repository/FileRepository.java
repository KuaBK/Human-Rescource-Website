package com.Phong.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Phong.BackEnd.entity.files.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {}
