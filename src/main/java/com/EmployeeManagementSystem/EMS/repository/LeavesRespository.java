package com.EmployeeManagementSystem.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.EMS.Entity.LeaveRequest;

@Repository
public interface LeavesRespository extends JpaRepository<LeaveRequest, Long> {

}
