package com.EmployeeManagementSystem.EMS.dto;

import com.EmployeeManagementSystem.EMS.Entity.LeaveStatus;

import lombok.Data;

@Data
public class LeaveActionDto {

	LeaveStatus leaveStatus;
	long employeeId;
}
