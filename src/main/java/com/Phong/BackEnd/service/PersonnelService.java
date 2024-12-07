package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.dto.response.Personnel.PersonnelResponse;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personnel.Employee;
import com.Phong.BackEnd.entity.personnel.Manager;
import com.Phong.BackEnd.entity.personnel.Personnel;
import com.Phong.BackEnd.repository.PersonnelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    private final PersonnelRepository personnelRepository;

    public ApiResponse<List<PersonnelResponse>> getAllPersonnel() {
        List<Personnel> personnels = personnelRepository.findAll();

        List<PersonnelResponse> responses = personnels.stream()
                .map(personnel -> {
                    PersonnelResponse.PersonnelResponseBuilder responseBuilder = PersonnelResponse.builder()
                            .code(personnel.getCode())
                            .firstName(personnel.getFirstName())
                            .lastName(personnel.getLastName())
                            .email(personnel.getEmail())
                            .phone(personnel.getPhone())
                            .position(personnel.getPosition().name())
                            .gender(personnel.getGender().name())
                            .city(personnel.getCity())
                            .street(personnel.getStreet())
                            .avatar(personnel.getAvatar());

                    // Check if the personnel is an Employee or Manager
                    if (personnel instanceof Employee employee) {
                        Department department = employee.getDepartment();
                        if (department != null) {
                            responseBuilder.departmentName(department.getDepartmentName())
                                    .departmentID(department.getDepartmentId());
                        }
                    } else if (personnel instanceof Manager manager) {
                        Department department = manager.getDepartment();
                        if (department != null) {
                            responseBuilder.departmentName(department.getDepartmentName())
                                    .departmentID(department.getDepartmentId());
                        }
                    }

                    return responseBuilder.build();
                })
                .toList();
        return ApiResponse.<List<PersonnelResponse>>builder()
                .code(2000)
                .message("Fetched all personnel successfully")
                .result(responses)
                .build();
    }

    public ApiResponse<PersonnelResponse> getPersonnelByCode(Long personnelCode) {
        Personnel personnel = personnelRepository.findById(personnelCode)
                .orElseThrow(() -> new EntityNotFoundException("Personnel with code " + personnelCode + " not found"));

        PersonnelResponse.PersonnelResponseBuilder responseBuilder = PersonnelResponse.builder()
                .code(personnel.getCode())
                .firstName(personnel.getFirstName())
                .lastName(personnel.getLastName())
                .email(personnel.getEmail())
                .phone(personnel.getPhone())
                .position(personnel.getPosition().name())
                .gender(personnel.getGender().name())
                .city(personnel.getCity())
                .street(personnel.getStreet())
                .avatar(personnel.getAvatar());

        // Handle Employee and Manager specifics
        if (personnel instanceof Employee employee) {
            Department department = employee.getDepartment();
            if (department != null) {
                responseBuilder.departmentName(department.getDepartmentName())
                        .departmentID(department.getDepartmentId());
            }
        } else if (personnel instanceof Manager manager) {
            Department department = manager.getDepartment();
            if (department != null) {
                responseBuilder.departmentName(department.getDepartmentName())
                        .departmentID(department.getDepartmentId());
            }
        }

        PersonnelResponse response = responseBuilder.build();
        return ApiResponse.<PersonnelResponse>builder()
                .code(2000)
                .message("Personnel fetched successfully")
                .result(response)
                .build();
    }
}
