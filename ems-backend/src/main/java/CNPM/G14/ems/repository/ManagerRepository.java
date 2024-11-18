package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Optional<Manager> findByCode(int code);
}
