package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.EmployeeInProject;
import CNPM.G14.ems.entity.ProjectEmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<EmployeeInProject, ProjectEmployeeId> {
    boolean existsById(ProjectEmployeeId id);
}
