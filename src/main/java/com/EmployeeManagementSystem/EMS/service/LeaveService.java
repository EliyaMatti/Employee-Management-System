package com.EmployeeManagementSystem.EMS.service;

import com.EmployeeManagementSystem.EMS.Entity.LeaveStatus;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.LeaveRequestDTO;

import jakarta.validation.Valid;

public interface LeaveService {

	void applyLeave(LeaveRequestDTO leaveRequestDTO) throws NoDataFoundException;

	void takeAction( LeaveStatus leaveStatus,long employeeId);

}
