package com.Phong.identityservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.identityservice.entity.File;
import com.Phong.identityservice.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file) {
        File uploadedFile = fileService.uploadFile(file);
        return ResponseEntity.ok(uploadedFile);
    }

    @GetMapping
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> files = fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    // Lấy tệp theo ID
    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        File file = fileService.getFileById(id);
        return ResponseEntity.ok(file);
    }
}
