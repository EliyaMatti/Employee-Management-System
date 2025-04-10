package com.EmployeeManagementSystem.EMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.EMS.Entity.LeaveRequest;

@Repository
public interface LeavesRespository extends JpaRepository<LeaveRequest, Long> {

//	Optional<LeaveRequest> findByEmployeeCode(LeaveRequest leaveRequest );

	@Query(value ="select * from leave_requests where employee_code = ?" , nativeQuery = true)
	List<LeaveRequest> findByEmployee(long employee_code);

}
