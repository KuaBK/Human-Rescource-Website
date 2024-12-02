package com.Phong.BackEnd.controller;

import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Personnel.PersonnelResponse;
import com.Phong.BackEnd.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/personnel")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelService personnelService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonnelResponse>>> getAllPersonnel() {
        ApiResponse<List<PersonnelResponse>> response = personnelService.getAllPersonnel();
        return ResponseEntity.ok(response);
    }


}
