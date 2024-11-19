package com.Phong.identityservice.service;

import java.io.IOException;
import java.util.Map;

import com.Phong.identityservice.entity.personel.Personel;
import com.Phong.identityservice.repository.EmployeeRepository;
import com.Phong.identityservice.repository.PersonelRepository;
import com.Phong.identityservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Phong.identityservice.entity.images.Image;
import com.Phong.identityservice.repository.ImageRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final Cloudinary cloudinary;
    private final PersonelRepository personelRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public ImageService(Cloudinary cloudinary, ImageRepository imageRepository,
                        PersonelRepository personelRepository, JwtUtils jwtUtils) {
        this.cloudinary = cloudinary;
        this.imageRepository = imageRepository;
        this.personelRepository = personelRepository;
        this.jwtUtils = jwtUtils;
    }

    public Image uploadImage(MultipartFile file, String token) throws IOException {
        String username = jwtUtils.getUsernameFromToken(token);

        Personel personel = personelRepository.findByAccountUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("url");
        String cloudinaryId = (String) uploadResult.get("public_id");

        // Lưu thông tin ảnh vào bảng Image
        Image image = new Image();
        image.setUrl(url);
        image.setCloudinaryId(cloudinaryId);
        image.setName(file.getOriginalFilename());
        image.setUploadedBy(personel);
        imageRepository.save(image);

        personel.setAvatar(url);
        personelRepository.save(personel);

        return image;
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
