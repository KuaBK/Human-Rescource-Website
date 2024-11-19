package com.Phong.identityservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.identityservice.entity.images.Image;
import com.Phong.identityservice.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageUploadService) {
        this.imageService = imageUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("image") MultipartFile file,
                                             @RequestHeader("Authorization") String token) throws IOException {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        Image image = imageService.uploadImage(file, jwtToken);

        return ResponseEntity.ok(image);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        return ResponseEntity.ok(image.getUrl());
    }
}
