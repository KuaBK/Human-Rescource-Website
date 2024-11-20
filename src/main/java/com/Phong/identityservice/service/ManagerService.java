package com.Phong.identityservice.service;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.identityservice.dto.request.Manager.ManagerCreateRequest;
import com.Phong.identityservice.dto.request.Manager.ManagerUpdateRequest;
import com.Phong.identityservice.dto.response.Manager.ManagerResponse;
import com.Phong.identityservice.entity.Account;
import com.Phong.identityservice.entity.departments.Department;
import com.Phong.identityservice.entity.personel.Manager;
import com.Phong.identityservice.entity.personel.Position;
import com.Phong.identityservice.repository.*;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository; // Add the DepartmentRepository

    @Autowired
    public ManagerService(
            ManagerRepository managerRepository,
            AccountRepository accountRepository,
            DepartmentRepository departmentRepository) {
        this.managerRepository = managerRepository;
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;
    }

    public ManagerResponse createManager(ManagerCreateRequest request) {
        Account account = accountRepository
                .findById(request.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account không tồn tại"));

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository
                    .findById(request.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));
        }
        Position position = (request.getPosition() != null) ? request.getPosition() : Position.MANAGER;

        Manager manager = Manager.builder()
                .account(account)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .avatar(request.getAvatar())
                .sex(request.getSex())
                .department(department)
                .position(position)
                .manageDate(LocalDate.now())
                .build();

        Manager savedManager = managerRepository.save(manager);

        return ManagerResponse.builder()
                .personelCode(savedManager.getPersonelCode())
                .firstName(savedManager.getFirstName())
                .lastName(savedManager.getLastName())
                .email(savedManager.getEmail())
                .phone(savedManager.getPhone())
                .address(savedManager.getAddress())
                .avatar(savedManager.getAvatar())
                .sex(savedManager.getSex())
                .departmentName(department != null ? department.getDepartmentName() : null)
                .position(savedManager.getPosition())
                .manageDate(savedManager.getManageDate())
                .build();
    }

    public Manager patchManager(Long id, ManagerUpdateRequest updates) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));

        if (updates.getEmail() != null) manager.setEmail(updates.getEmail());
        if (updates.getFirstName() != null) manager.setFirstName(updates.getFirstName());
        if (updates.getLastName() != null) manager.setLastName(updates.getLastName());
        if (updates.getPhone() != null) manager.setPhone(updates.getPhone());
        if (updates.getAddress() != null) manager.setAddress(updates.getAddress());
        if (updates.getSex() != null) manager.setSex(updates.getSex());
        if (updates.getPosition() != null) manager.setPosition(updates.getPosition());
        if (updates.getDepartmentId() != null) {
            Department department = departmentRepository
                    .findById(updates.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department không tồn tại"));
            manager.setDepartment(department);
        }

        return managerRepository.save(manager);
    }

    public void deleteEmployee(Long personelCode) {
        managerRepository.deleteById(personelCode);
    }

    public Manager getManagerByPersonelCode(Long personelCode) {
        return managerRepository
                .findById(personelCode)
                .orElseThrow(() -> new EntityNotFoundException("Manager không tồn tại"));
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public ManagerResponse toDto(Manager manager) {
        Department department = manager.getDepartment();
        return ManagerResponse.builder()
                .personelCode(manager.getPersonelCode())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .email(manager.getEmail())
                .phone(manager.getPhone())
                .address(manager.getAddress())
                .sex(manager.getSex())
                .position(manager.getPosition())
                .manageDate(manager.getManageDate())
                .departmentName(department != null ? department.getDepartmentName() : null)
                .build();
    }
}
