package com.Phong.BackEnd.controller;

import java.util.List;

import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.File.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.BackEnd.entity.files.File;
import com.Phong.BackEnd.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<FileResponse>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String token) {
        FileResponse uploadedFile = fileService.uploadFile(file, token);
        return ResponseEntity.ok(ApiResponse.<FileResponse>builder()
                .code(1000)
                .message("File uploaded successfully")
                .result(uploadedFile)
                .build());
    }

    @GetMapping("/personel/{personelCode}")
    public ResponseEntity<ApiResponse<List<File>>> getAllFilesByPersonel(@PathVariable Long personelCode) {
        List<File> files = fileService.getAllFilesByPersonel(personelCode);
        return ResponseEntity.ok(ApiResponse.<List<File>>builder()
                .code(1000)
                .message("Fetched files successfully")
                .result(files)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        File file = fileService.getFileById(id);
        return ResponseEntity.ok(file);
    }
}
