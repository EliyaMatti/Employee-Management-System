package com.EmployeeManagementSystem.EMS.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.EMS.Expection.AlreadyActionTakenException;
import com.EmployeeManagementSystem.EMS.Expection.InsufficientLeaveException;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.LeaveActionDto;
import com.EmployeeManagementSystem.EMS.dto.LeaveRequestDTO;
import com.EmployeeManagementSystem.EMS.service.LeaveService;

@RestController
@RequestMapping("/ems/employee")
public class LeaveController {

	private LeaveService leaveService;

	public LeaveController(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

	@PostMapping("/leaves/applyLeave")
	public ResponseEntity<?> ApplyLeave(@RequestBody LeaveRequestDTO leaveRequestDTO)
			throws NoDataFoundException, InsufficientLeaveException {
		leaveService.applyLeave(leaveRequestDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Leave Applied");

	}

	@PutMapping("/leaves/Leaveaction")
	public ResponseEntity<?> leaveaction(@RequestBody LeaveActionDto leaveActionDto ) throws NoDataFoundException,AlreadyActionTakenException {
		leaveService.takeAction(leaveActionDto);
		return ResponseEntity.status(HttpStatus.OK).body("leave "+ leaveActionDto.getLeaveStatus().toString());
	}
}
