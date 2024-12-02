package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Personnel.PersonnelResponse;
import com.Phong.BackEnd.entity.personnel.Personnel;
import com.Phong.BackEnd.repository.PersonnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    private final PersonnelRepository personnelRepository;

    public ApiResponse<List<PersonnelResponse>> getAllPersonnel() {
        List<Personnel> personnels = personnelRepository.findAll();

        List<PersonnelResponse> personnelResponses = personnels.stream()
                .map(personnel -> PersonnelResponse.builder()
                        .code(personnel.getCode())
                        .firstName(personnel.getFirstName())
                        .lastName(personnel.getLastName())
                        .email(personnel.getEmail())
                        .phone(personnel.getPhone())
                        .position(personnel.getPosition().name())
                        .gender(personnel.getGender().name())
                        .city(personnel.getCity())
                        .street(personnel.getStreet())
                        .avatar(personnel.getAvatar())
                        .build())
                .toList();

        return ApiResponse.<List<PersonnelResponse>>builder()
                .code(2000)
                .message("Fetched all personnel successfully")
                .result(personnelResponses)
                .build();
    }
}
