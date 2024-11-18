//package CNPM.G14.ems.service.impl;
//
//import CNPM.G14.ems.entity.Image;
//import CNPM.G14.ems.repository.ImageRepository;
//import CNPM.G14.ems.service.ImageService;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//
//import lombok.AllArgsConstructor;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@AllArgsConstructor
//@Service
//public class ImageServiceImpl implements ImageService {
//
//    private ImageRepository imageRepository;
//    private final Cloudinary cloudinary;
//
//    @Override
//    public Image uploadImage(MultipartFile file) throws IOException {
//        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//
//        String url = (String) uploadResult.get("url");
//        String cloudinaryId = (String) uploadResult.get("public_id");
//
//        Image image = new Image();
//        image.setUrl(url);
//        image.setCloudID(cloudinaryId);
//        image.setName(file.getOriginalFilename());
//
//        return imageRepository.save(image);
//    }
//
////    @Override
////    public Image saveImageUrl(String imageUrl, String name) {
////        Image img = new Image();
////        img.setName(name);
////        img.setUrl(imageUrl);
////        return imageRepository.save(img);
////    }
//
//    @Override
//    public Image getImage(String url) {
//        return imageRepository.findByUrl(url).orElseThrow(() -> new RuntimeException("Image not found"));
//    }
//}
