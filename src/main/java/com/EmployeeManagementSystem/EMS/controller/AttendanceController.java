package com.EmployeeManagementSystem.EMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.service.AttendenceService;

@RestController
@RequestMapping("/ems/employee")
public class AttendanceController {

	@Autowired
	AttendenceService attendenceService;

	@PostMapping("/attendance/checkIn/{employeeCode}")
	public void checkin(@PathVariable long employeeCode) throws NoDataFoundException {
		attendenceService.markAttendence(employeeCode);
	}
}
