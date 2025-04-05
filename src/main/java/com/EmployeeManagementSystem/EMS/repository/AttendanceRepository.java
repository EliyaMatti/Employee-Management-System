package com.EmployeeManagementSystem.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.EMS.Entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
