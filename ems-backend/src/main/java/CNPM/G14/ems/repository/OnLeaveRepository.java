package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.OnLeave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OnLeaveRepository extends JpaRepository<OnLeave, String> {
    List<OnLeave> findAllByEmployee_Code(int employeeID);
}
