package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByCode(int employeeCode);
    List<Employee> findByDepartmentId(int departmentId);
}
