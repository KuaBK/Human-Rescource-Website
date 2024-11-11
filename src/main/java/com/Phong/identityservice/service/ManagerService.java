package com.Phong.identityservice.service;

import com.Phong.identityservice.entity.personel.Manager;
import com.Phong.identityservice.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    // Create a new manager
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    // Get a manager by ID
    public Optional<Manager> getManagerById(Long id) {
        return managerRepository.findById(id);
    }

    // Get all managers
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    // Update an existing manager
    public Manager updateManager(Long id, Manager managerDetails) {
        return managerRepository.findById(id).map(manager -> {
            manager.setManageDate(managerDetails.getManageDate());
            manager.setFirstName(managerDetails.getFirstName());
            manager.setLastName(managerDetails.getLastName());
            manager.setPosition(managerDetails.getPosition());
            manager.setSex(managerDetails.getSex());
            manager.setEmail(managerDetails.getEmail());
            manager.setPhone(managerDetails.getPhone());
            manager.setAddress(managerDetails.getAddress());
            manager.setDepartment(managerDetails.getDepartment());
            return managerRepository.save(manager);
        }).orElseThrow(() -> new RuntimeException("Manager not found with id " + id));
    }

    // Delete a manager
    public void deleteManager(Long id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Manager not found with id " + id);
        }
    }
}
