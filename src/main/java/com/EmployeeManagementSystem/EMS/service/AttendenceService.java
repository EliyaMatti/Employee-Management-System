package com.EmployeeManagementSystem.EMS.service;

import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;

public interface AttendenceService {

	void markAttendence(long employeeCode) throws NoDataFoundException;
}
