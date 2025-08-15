package com.employeemanagementsystem.ems.service;

import com.employeemanagementsystem.ems.dto.LeaveActionDto;
import com.employeemanagementsystem.ems.dto.LeaveRequestDTO;
import com.employeemanagementsystem.ems.expection.AlreadyActionTakenException;
import com.employeemanagementsystem.ems.expection.InsufficientLeaveException;
import com.employeemanagementsystem.ems.expection.NoDataFoundException;

public interface LeaveService {

  void applyLeave(LeaveRequestDTO leaveRequestDTO)
      throws NoDataFoundException, InsufficientLeaveException;

  void takeAction(LeaveActionDto leaveActionDto)
      throws NoDataFoundException, AlreadyActionTakenException;

  Integer leaveBalance(long employeeId) throws NoDataFoundException;
}
