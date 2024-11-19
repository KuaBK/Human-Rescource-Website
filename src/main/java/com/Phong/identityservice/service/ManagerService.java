package com.Phong.identityservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.Phong.identityservice.entity.departments.Department;
import com.Phong.identityservice.entity.personel.Position;
import com.Phong.identityservice.entity.personel.Sex;
import com.Phong.identityservice.repository.DepartmentRepository;
import com.Phong.identityservice.repository.ImageRepository;
import com.Phong.identityservice.repository.PersonelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Phong.identityservice.entity.personel.Manager;
import com.Phong.identityservice.repository.ManagerRepository;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ImageRepository imageRepository;
    private final PersonelRepository personelRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository,ImageRepository imageRepository,
                          PersonelRepository personelRepository) {
        this.managerRepository = managerRepository;
        this.imageRepository = imageRepository;
        this.personelRepository = personelRepository;
    }


    public Manager createManager(Manager manager) {
        if (manager.getPosition() == null) {
            manager.setPosition(Position.MANAGER);
        }
        return managerRepository.save(manager);
    }

    public Optional<Manager> getManagerById(Long id) {
        return managerRepository.findById(id);
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Manager patchManager(Long id, Map<String, Object> updates) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager không tồn tại với id " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "email":
                    manager.setEmail((String) value);
                    break;
                case "firstName":
                    manager.setFirstName((String) value);
                    break;
                case "lastName":
                    manager.setLastName((String) value);
                    break;
                case "position":
                    manager.setPosition(Position.valueOf((String) value));
                    break;
                case "sex":
                    manager.setSex(Sex.valueOf((String) value));
                    break;
                case "phone":
                    manager.setPhone((String) value);
                    break;
                case "address":
                    manager.setAddress((String) value);
                    break;
                case "department":
                    manager.setDepartment((Department) value);
                    break;
                case "manageDate":
                    manager.setManageDate(LocalDate.parse((String) value));
                    break;
                default:
                    throw new IllegalArgumentException("Trường " + key + " không hợp lệ");
            }
        });
        return managerRepository.save(manager);
    }

        @Transactional
        public void deleteManager(Long personelCode) {
            Manager manager = managerRepository.findById(personelCode)
                .orElseThrow(() -> new RuntimeException("Manager không tồn tại"));

            imageRepository.deleteByUploadedBy(manager);

            managerRepository.delete(manager);
            personelRepository.delete(manager);
        }
}

