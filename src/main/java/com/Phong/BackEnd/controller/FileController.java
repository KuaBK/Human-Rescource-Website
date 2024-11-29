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
            @RequestParam Long id) {
        FileResponse uploadedFile = fileService.uploadFile(file, id);
        return ResponseEntity.ok(ApiResponse.<FileResponse>builder()
                .code(1000)
                .message("File uploaded successfully")
                .result(uploadedFile)
                .build());
    }

    @GetMapping("/personnel")
    public ResponseEntity<ApiResponse<List<File>>> getAllFilesByPersonel(@RequestParam Long code) {
        List<File> files = fileService.getAllFilesByPersonel(code);
        return ResponseEntity.ok(ApiResponse.<List<File>>builder()
                .code(1000)
                .message("Fetched files successfully")
                .result(files)
                .build());
    }

    @GetMapping()
    public ResponseEntity<File> getFileById(@RequestParam Long id) {
        File file = fileService.getFileById(id);
        return ResponseEntity.ok(file);
    }
}
