package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.PersonnelCreationRequest;
import CNPM.G14.ems.dto.request.PersonnelUpdateRequest;
import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.dto.response.PersonelResponse;
import CNPM.G14.ems.exception.AppException;
import CNPM.G14.ems.service.PersonelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/personel")
public class PersonelController {

    @Autowired
    private final PersonelService personelService;

    @PostMapping("/add")
    public ApiResponse<PersonelResponse> addPersonel(@RequestBody PersonnelCreationRequest request){
        try{
            PersonelResponse response = personelService.createPersonel(request);
            return ApiResponse.<PersonelResponse>builder()
                    .EC(0)
                    .EM("Add personel with code: " + response.getCode())
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<PersonelResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<PersonelResponse>> getAllPersonel(){
        try {
            List<PersonelResponse> list = personelService.getAllPersonel();
            return ApiResponse.<List<PersonelResponse>>builder()
                    .EC(0)
                    .EM("Fetch data success")
                    .data(list)
                    .build();
        } catch (Exception e){
            return ApiResponse.<List<PersonelResponse>>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<PersonelResponse> getPersonel(@RequestParam int code){
        try {
            PersonelResponse personel = personelService.getPersonel(code);
            return ApiResponse.<PersonelResponse>builder()
                    .EC(0)
                    .EM("Fetch personel data success")
                    .data(personel)
                    .build();
        } catch (Exception e){
            return ApiResponse.<PersonelResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deletePersonel(@RequestParam int code) {
        try {
            personelService.deletePersonel(code);
            return ApiResponse.<String>builder()
                    .EC(0)
                    .EM("Delete personel success!")
                    .data("Personel with code: " + code + " has been deleted")
                    .build();
        } catch (AppException e) {
            return ApiResponse.<String>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data("Not found personel with code " + code)
                    .build();
        }
    }

    @PutMapping("/edit")
    ApiResponse<PersonelResponse> updatePersonel(@RequestParam int code, @RequestBody PersonnelUpdateRequest request){
        try {
            PersonelResponse response = personelService.updatePersonel(code, request);
            return ApiResponse.<PersonelResponse>builder()
                    .EC(0)
                    .EM("Update personel succeed")
                    .data(response)
                    .build();
        } catch (AppException e) {
            return ApiResponse.<PersonelResponse>builder()
                    .EC(-1)
                    .EM("Not found personel with code " + code)
                    .data(null)
                    .build();
        }
    }
}