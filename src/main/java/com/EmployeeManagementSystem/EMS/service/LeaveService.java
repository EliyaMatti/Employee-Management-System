package com.EmployeeManagementSystem.EMS.service;

import com.EmployeeManagementSystem.EMS.Expection.AlreadyActionTakenException;
import com.EmployeeManagementSystem.EMS.Expection.InsufficientLeaveException;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.LeaveActionDto;
import com.EmployeeManagementSystem.EMS.dto.LeaveRequestDTO;

public interface LeaveService {

  void applyLeave(LeaveRequestDTO leaveRequestDTO)
      throws NoDataFoundException, InsufficientLeaveException;

  void takeAction(LeaveActionDto leaveActionDto)
      throws NoDataFoundException, AlreadyActionTakenException;

  Integer leaveBalance(long employeeId) throws NoDataFoundException;
}
