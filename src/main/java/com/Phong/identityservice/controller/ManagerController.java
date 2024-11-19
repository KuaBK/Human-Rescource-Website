package com.Phong.identityservice.controller;

import java.util.List;
import java.util.Map;

import com.Phong.identityservice.entity.personel.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Phong.identityservice.entity.personel.Manager;
import com.Phong.identityservice.service.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    // Create a new manager
    @PostMapping
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager) {
        Manager createdManager = managerService.createManager(manager);
        return ResponseEntity.ok(createdManager);
    }

    // Get a manager by ID
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        return managerService
                .getManagerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all managers
    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers() {
        return ResponseEntity.ok(managerService.getAllManagers());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Manager> patchManager(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> updates) {
        Manager updatedManager = managerService.patchManager(id, updates);
        return ResponseEntity.ok(updatedManager);
    }

    // Delete a manager
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}
