package com.Phong.BackEnd.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.BackEnd.entity.File;
import com.Phong.BackEnd.repository.FileRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class FileService {

    private final Cloudinary cloudinary;
    private final FileRepository fileRepository;

    @Autowired
    public FileService(Cloudinary cloudinary, FileRepository fileRepository) {
        this.cloudinary = cloudinary;
        this.fileRepository = fileRepository;
    }

    public File uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        try {

            Map<?, ?> uploadResult = cloudinary
                    .uploader()
                    .upload(
                            file.getBytes(),
                            ObjectUtils.asMap(
                                    "resource_type", "auto",
                                    "folder", "uploads"));

            File uploadedFile = File.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileUrl(uploadResult.get("secure_url").toString())
                    .uploadDate(new Date())
                    .build();

            return fileRepository.save(uploadedFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public File getFileById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }
}
