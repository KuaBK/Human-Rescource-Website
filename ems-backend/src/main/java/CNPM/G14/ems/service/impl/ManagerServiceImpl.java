package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.response.ManagerResponse;
import CNPM.G14.ems.entity.Department;
import CNPM.G14.ems.entity.Manager;
import CNPM.G14.ems.entity.Personnel;
import CNPM.G14.ems.exception.ResourceNotFoundException;
import CNPM.G14.ems.repository.DepartmentRepository;
import CNPM.G14.ems.repository.ManagerRepository;
import CNPM.G14.ems.repository.PersonnelRepository;
import CNPM.G14.ems.service.ManagerService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagerServiceImpl implements ManagerService {

    ManagerRepository managerRepository;
    DepartmentRepository departmentRepository;
    PersonnelRepository personnelRepository;

    @Override
    @Transactional
    public ManagerResponse assignManagerToDept(int code, int deptId){
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

        return new ManagerResponse(
                manager.getCode(),
                department.getId(),
                manager.getManageDate()
        );
    }

    @Override
    @Transactional
    public void removeManagerFromDepartment(int deptId) {
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

    @Override
    @Transactional
    public int addManager(int code){
        Personnel personel = personnelRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Personel not found with code: " + code));

        if (!Objects.equals(personel.getPosition(), "MANAGER")) {
            throw new IllegalStateException("Personel is not a manager.");
        }

        Manager manager = new Manager();
        manager.setPersonnel(personel);
        manager.setManageDate(null);
        manager.setDepartment(null);

        managerRepository.save(manager);

        return manager.getCode();
    }

    @Override
    public ManagerResponse getManager(int code) {
        Manager manager =  managerRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with code: " + code));

        if (manager.getDepartment() == null) {
            return new ManagerResponse(
                    manager.getCode(),
                    -1,  // Use -1 to indicate no department is assigned
                    manager.getManageDate()
            );
        }

        return new ManagerResponse(
                manager.getCode(),
                manager.getDepartment().getId(),
                manager.getManageDate()
        );
    }

    @Override
    public List<ManagerResponse> getAllManagers(){
        return managerRepository.findAll().stream()
                .map(manager -> new ManagerResponse(
                        manager.getCode(),
                        manager.getDepartment().getId(),
                        manager.getManageDate()
                ))
                .toList();
    }

}

