package com.Phong.BackEnd.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.entity.images.Image;
import com.Phong.BackEnd.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ApiResponse<String> uploadImage(
            @RequestParam("image") MultipartFile file, @RequestHeader("Authorization") String token)
            throws IOException {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        Image image = imageService.uploadImage(file, jwtToken);

        return ApiResponse.<String>builder()
                .code(1000)
                .message("Image uploaded successfully")
                .result(image.getUrl())
                .build();
    }

    @GetMapping()
    public ApiResponse<String> getImage(@RequestParam Long id) {
        Image image = imageService.getImage(id);
        return ApiResponse.<String>builder()
                .code(1000)
                .message("Image retrieved successfully")
                .result(image.getUrl())
                .build();
    }
}
