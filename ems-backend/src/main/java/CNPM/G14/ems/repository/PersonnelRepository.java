package CNPM.G14.ems.repository;

import CNPM.G14.ems.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    // Optional<Personnel> findByAccountId(String id);
    Optional<Personnel> findByCode(int code);
}
