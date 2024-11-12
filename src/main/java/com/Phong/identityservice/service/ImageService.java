package com.Phong.identityservice.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.identityservice.entity.images.Image;
import com.Phong.identityservice.repository.ImageRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private final Cloudinary cloudinary;

    @Autowired
    public ImageService(Cloudinary cloudinary, ImageRepository imageRepository) {
        this.cloudinary = cloudinary;
        this.imageRepository = imageRepository;
    }

    public Image uploadImage(MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        String url = (String) uploadResult.get("url");
        String cloudinaryId = (String) uploadResult.get("public_id");

        Image image = new Image();
        image.setUrl(url);
        image.setCloudinaryId(cloudinaryId);
        image.setName(file.getOriginalFilename());

        return imageRepository.save(image);
    }

    public Image saveImageUrl(String imageUrl, String name) {
        Image img = new Image();
        img.setName(name);
        img.setUrl(imageUrl);
        return imageRepository.save(img);
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
