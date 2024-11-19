package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.Employee;
import CNPM.G14.ems.entity.SalaryBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryBoardRepository extends JpaRepository<SalaryBoard, Long> {
    Optional<SalaryBoard> findByEmployeeAndMonthAndYear(Employee employee, int month, int year);
    boolean existsByEmployeeAndMonthAndYear(Employee employee, int month, int year);
    List<SalaryBoard> findAllByEmployee(Employee employee);
}
