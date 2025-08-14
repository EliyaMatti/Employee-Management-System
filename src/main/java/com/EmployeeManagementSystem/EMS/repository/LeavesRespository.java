package com.EmployeeManagementSystem.EMS.repository;

import com.EmployeeManagementSystem.EMS.Entity.LeaveRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavesRespository extends JpaRepository<LeaveRequest, Long> {

  //	Optional<LeaveRequest> findByEmployeeCode(LeaveRequest leaveRequest );

  @Query(value = "select * from leave_requests where employee_code = ?", nativeQuery = true)
  List<LeaveRequest> findByEmployee(long employee_code);

  @Query(
      value =
          "select MIN(leave_balance) from leave_requests where employee_code = ? and status = 'Approved'",
      nativeQuery = true)
  Integer leaveBalance(long employee_code);
}
