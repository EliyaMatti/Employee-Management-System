package com.EmployeeManagementSystem.EMS.service;

import java.util.List;

import com.EmployeeManagementSystem.EMS.Expection.AlreadyCheckedInException;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.AttendanceDTO;

public interface AttendenceService {

	void markAttendence(long employeeCode) throws NoDataFoundException, AlreadyCheckedInException;

	void completeAttendence(long employeeCode)throws NoDataFoundException;

	List<AttendanceDTO> AttendenceList(long employeeCode) throws NoDataFoundException;
}
