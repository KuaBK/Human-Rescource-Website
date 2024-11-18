package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.Department;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // Optional<Department> findDepartmentById(Integer integer);
}
