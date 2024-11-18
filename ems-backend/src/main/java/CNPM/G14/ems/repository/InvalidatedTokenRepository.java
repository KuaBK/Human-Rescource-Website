package CNPM.G14.ems.repository;
import CNPM.G14.ems.entity.InvalidatedToken;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String>{}
