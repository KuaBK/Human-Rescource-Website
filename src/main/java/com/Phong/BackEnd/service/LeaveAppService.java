package com.Phong.BackEnd.service;

import com.Phong.BackEnd.dto.request.LeaveApplicatonResquest.LeaveApplicationRequest;
import com.Phong.BackEnd.dto.response.LeaveAppllication.LeaveApplicationResponse;
import com.Phong.BackEnd.entity.leaveApplication.LeaveApplication;
import com.Phong.BackEnd.entity.personel.Employee;
import com.Phong.BackEnd.repository.EmployeeRepository;
import com.Phong.BackEnd.repository.LeaveApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveAppService {

    private final LeaveApplicationRepository leaveApplicationRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveApplicationResponse createOnLeaveLetter(LeaveApplicationRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        LeaveApplication leaveApplication = LeaveApplication.builder()
                .employee(employee)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .reason(request.getReason())
                .approved(false)
                .build();

        LeaveApplication savedLeaveApplication = leaveApplicationRepository.save(leaveApplication);

        return toDto(savedLeaveApplication);
    }

    public List<LeaveApplicationResponse> getAllOnLeaveByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findByEmployee(employee);

        return leaveApplications.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LeaveApplicationResponse toDto(LeaveApplication leaveApplication) {
        return LeaveApplicationResponse.builder()
                .id(leaveApplication.getId())
                .employeeId(leaveApplication.getEmployee().getPersonelCode())
                .employeeName(leaveApplication.getEmployee().getFirstName() + " " + leaveApplication.getEmployee().getLastName())
                .startDate(leaveApplication.getStartDate())
                .endDate(leaveApplication.getEndDate())
                .reason(leaveApplication.getReason())
                .approved(leaveApplication.getApproved())
                .build();
    }
}

