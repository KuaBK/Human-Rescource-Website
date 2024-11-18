package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.Employee;
import CNPM.G14.ems.entity.Project;
import CNPM.G14.ems.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByProject(Project project);
    List<Task> findByEmployee(Employee employee);
}
