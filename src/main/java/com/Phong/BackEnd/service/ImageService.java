package com.Phong.BackEnd.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.BackEnd.entity.images.Image;
import com.Phong.BackEnd.entity.personnel.Personnel;
import com.Phong.BackEnd.repository.ImageRepository;
import com.Phong.BackEnd.repository.PersonnelRepository;
import com.Phong.BackEnd.utils.JwtUtils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final Cloudinary cloudinary;
    private final PersonnelRepository personnelRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public ImageService(
            Cloudinary cloudinary,
            ImageRepository imageRepository,
            PersonnelRepository personnelRepository,
            JwtUtils jwtUtils) {
        this.cloudinary = cloudinary;
        this.imageRepository = imageRepository;
        this.personnelRepository = personnelRepository;
        this.jwtUtils = jwtUtils;
    }

    public Image uploadImage(MultipartFile file, String token) throws IOException {

        String contentType = file.getContentType();
        if (contentType == null ||
                (!contentType.equals("image/png") && !contentType.equals("image/jpeg"))) {
            throw new IllegalArgumentException("Chỉ cho phép tải lên tệp hình ảnh (PNG, JPEG)");
        }

        String username = jwtUtils.getUsernameFromToken(token);

        Personnel personnel = personnelRepository
                .findByAccountUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("url");
        String cloudinaryId = (String) uploadResult.get("public_id");

        Image image = new Image();
        image.setUrl(url);
        image.setCloudinaryId(cloudinaryId);
        image.setName(file.getOriginalFilename());
        image.setUploadedBy(personnel);
        imageRepository.save(image);

        personnel.setAvatar(url);
        personnelRepository.save(personnel);

        return image;
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
