package com.Phong.BackEnd.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.Phong.BackEnd.dto.response.File.FileResponse;
import com.Phong.BackEnd.entity.personel.Personel;
import com.Phong.BackEnd.repository.PersonelRepository;
import com.Phong.BackEnd.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.BackEnd.entity.files.File;
import com.Phong.BackEnd.repository.FileRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class FileService {

    private final Cloudinary cloudinary;
    private final FileRepository fileRepository;
    private final JwtUtils jwtUtils;
    private final PersonelRepository personelRepository;

    @Autowired
    public FileService(Cloudinary cloudinary, FileRepository fileRepository,
                       JwtUtils jwtUtils, PersonelRepository personelRepository) {
        this.cloudinary = cloudinary;
        this.fileRepository = fileRepository;
        this.jwtUtils = jwtUtils;
        this.personelRepository = personelRepository;
    }

    public FileResponse uploadFile(MultipartFile file, Long id) {

        Personel personel = personelRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra file rỗng
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        try {
            String fileType = file.getContentType();

            if (!"application/pdf".equals(fileType) &&
                    !"application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(fileType)) {
                throw new IllegalArgumentException("Only PDF and DOCX files are supported");
            }

            String resourceType = "raw"; 
            String format = "application/pdf".equals(fileType) ? "pdf" : "docx";

//            // Phân loại file dựa trên MIME type
//            if ("application/pdf".equals(fileType)) {
//                resourceType = "image"; // Xử lý file PDF
//                format = "pdf";
//            } else if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(fileType)) {
//                resourceType = "raw"; // Xử lý file DOCX
//                format = "docx";
//            } else if (fileType.startsWith("image/")) {
//                resourceType = "image"; // Xử lý file ảnh
//                format = fileType.substring("image/".length());
//            } else {
//                throw new IllegalArgumentException("Unsupported file type: " + fileType);
//            }

            // Upload file lên Cloudinary
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", resourceType, "format", format)
            );

            String fileUrl = uploadResult.get("secure_url").toString();

            File uploadedFile = File.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(format)
                    .fileUrl(fileUrl)
                    .uploadDate(new Date())
                    .uploadedBy(personel)
                    .build();

            File savedFile = fileRepository.save(uploadedFile);

            if (personel.getFiles() != null) {
                personel.getFiles().add(savedFile);
            } else {
                personel.setFiles(List.of(savedFile));
            }
            personelRepository.save(personel);

            return FileResponse.builder()
                    .id(savedFile.getId())
                    .fileName(savedFile.getFileName())
                    .fileType(savedFile.getFileType())
                    .fileUrl(savedFile.getFileUrl())
                    .uploadDate(savedFile.getUploadDate())
                    .uploadedBy(personel.getFirstName() + " " + personel.getLastName())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public List<File> getAllFilesByPersonel(Long personelCode) {
        Personel personel = personelRepository.findById(personelCode)
                .orElseThrow(() -> new RuntimeException("Personel not found with code: " + personelCode));
        return personel.getFiles();
    }

    public File getFileById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }
}
