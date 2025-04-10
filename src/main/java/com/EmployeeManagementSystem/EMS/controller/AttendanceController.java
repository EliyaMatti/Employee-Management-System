package com.EmployeeManagementSystem.EMS.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.EMS.Expection.AlreadyCheckedInException;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.AttendanceDTO;
import com.EmployeeManagementSystem.EMS.service.AttendenceService;

@RestController
@RequestMapping("/ems/employee")
public class AttendanceController {

	private AttendenceService attendenceService;

	public AttendanceController(AttendenceService attendenceService) {
		this.attendenceService = attendenceService;
	}

	@PostMapping("/attendance/checkIn/{employeeCode}")
	public ResponseEntity<?> checkin(@PathVariable long employeeCode)
			throws NoDataFoundException, AlreadyCheckedInException {
		attendenceService.markAttendence(employeeCode);
		return ResponseEntity.status(HttpStatus.OK).body("clocked in");

	}

	@PatchMapping("/attendance/checkOut/{employeeCode}")
	public ResponseEntity<?> checkout(@PathVariable long employeeCode) throws NoDataFoundException {
		attendenceService.completeAttendence(employeeCode);
		return ResponseEntity.status(HttpStatus.OK).body("clocked out");
	}

	@GetMapping("/attendance/getAttendanceList/{employeeCode}")
	public ResponseEntity<List<AttendanceDTO>> getAtendancelist(@PathVariable long employeeCode)
			throws NoDataFoundException {
		List<AttendanceDTO> attendenceList = attendenceService.AttendenceList(employeeCode);
		return new ResponseEntity<>(attendenceList, HttpStatus.OK);

	}
}
