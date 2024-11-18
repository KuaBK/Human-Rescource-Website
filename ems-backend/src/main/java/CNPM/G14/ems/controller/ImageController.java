//package CNPM.G14.ems.controller;
//
//import CNPM.G14.ems.dto.response.ApiResponse;
//import CNPM.G14.ems.dto.response.ImageResponse;
//import CNPM.G14.ems.entity.Image;
//import CNPM.G14.ems.service.ImageService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@CrossOrigin("*")
//@RequiredArgsConstructor
//@RestController
//@Slf4j
//@RequestMapping("/image")
//public class ImageController {
//
//    private final ImageService imageService;
//
//    private ImageResponse toImageResponse(Image image) {
//        ImageResponse response = new ImageResponse();
//        response.setName(image.getName());
//        response.setUrl(image.getUrl());
//        return response;
//    }
//
//    @PostMapping("/upload")
//    public ApiResponse<ImageResponse> uploadImage(@RequestParam("file") MultipartFile file) {
//        try{
//            Image image = imageService.uploadImage(file);
//            return ApiResponse.<ImageResponse>builder()
//                    .EC(0)
//                    .EM("upload file success")
//                    .data(toImageResponse(image))
//                    .build();
//        } catch (Exception e){
//            return ApiResponse.<ImageResponse>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//
//    @GetMapping
//    public ApiResponse<Image> getImage(@RequestParam String url) {
//        try{
//            Image image = imageService.getImage(url);
//            return ApiResponse.<Image>builder()
//                    .EC(0)
//                    .EM("get image success")
//                    .data(image)
//                    .build();
//        } catch (Exception e){
//            return ApiResponse.<Image>builder()
//                    .EC(-1)
//                    .EM(e.getMessage())
//                    .data(null)
//                    .build();
//        }
//    }
//}
