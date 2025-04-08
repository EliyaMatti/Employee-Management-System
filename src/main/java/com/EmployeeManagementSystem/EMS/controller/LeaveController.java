package com.EmployeeManagementSystem.EMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.EMS.Entity.LeaveStatus;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.LeaveRequestDTO;
import com.EmployeeManagementSystem.EMS.service.LeaveService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ems/employee")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	
	@PostMapping("/leaves/applyLeave")
	public ResponseEntity<?> ApplyLeave( @RequestBody LeaveRequestDTO leaveRequestDTO) throws NoDataFoundException{
		leaveService.applyLeave(leaveRequestDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Leave Applied");
		
	}
	
	@PutMapping("/leaves/Leaveaction")
	public ResponseEntity<?> leaveaction(@Valid LeaveStatus leaveStatus ,@PathVariable long employeeId){
		leaveService.takeAction(leaveStatus,employeeId);
		return null;
	}
}
