package com.EmployeeManagementSystem.EMS.repository;

import com.EmployeeManagementSystem.EMS.Entity.Attendance;
import com.EmployeeManagementSystem.EMS.Entity.Employee;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

  boolean existsByEmployeeAndAttendanceDate(Employee employee, LocalDate today);

  Optional<Attendance> findByEmployeeAndAttendanceDate(Employee employee, LocalDate attendanceDate);

  List<Attendance> findAllByEmployee(Employee employee);

  //	@Query(value="select ")
  //	void checkIfAttendanceAlreadyMarked();

}
