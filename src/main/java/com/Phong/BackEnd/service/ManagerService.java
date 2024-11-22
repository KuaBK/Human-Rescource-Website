package com.Phong.BackEnd.service;

import java.time.LocalDate;
import java.util.List;

import com.Phong.BackEnd.dto.request.Manager.MTDResponse;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.BackEnd.dto.request.Manager.ManagerCreateRequest;
import com.Phong.BackEnd.dto.request.Manager.ManagerUpdateRequest;
import com.Phong.BackEnd.dto.response.Manager.ManagerResponse;
import com.Phong.BackEnd.entity.Account;
import com.Phong.BackEnd.entity.departments.Department;
import com.Phong.BackEnd.entity.personel.Manager;
import com.Phong.BackEnd.entity.personel.Position;
import com.Phong.BackEnd.repository.*;

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


    @Transactional
    public MTDResponse assignManagerToDept(Long code, Long deptId){
        Manager manager = managerRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Manager not found with code: " + code));

        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + deptId));

        if (department.getManager() != null) {
            removeManagerFromDepartment(deptId);
        }

        manager.setDepartment(department);
        manager.setManageDate(LocalDate.now());

        department.setManager(manager);

        managerRepository.save(manager);
        departmentRepository.save(department);

        return new MTDResponse(
                manager.getPersonelCode(),
                department.getDepartmentId(),
                manager.getManageDate()
        );
    }

    @Transactional
    public void removeManagerFromDepartment(Long deptId) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + deptId));

        Manager currentManager = department.getManager();

        if (currentManager != null) {
            department.setManager(null);

            currentManager.setDepartment(null);

            departmentRepository.save(department);
            managerRepository.save(currentManager);
        } else {
            throw new IllegalStateException("No manager assigned to this department.");
        }
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
