//package CNPM.G14.ems.repository;
//
//import CNPM.G14.ems.entity.Attendance;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface AttendanceRepository extends JpaRepository<Attendance, String> {
//    Optional<Attendance> findByEmployee_EmployeeCodeAndDate(int employeeID, LocalDate date);
//    List<Attendance> findAllByEmployee_EmployeeCode(int employeeId);
//}
