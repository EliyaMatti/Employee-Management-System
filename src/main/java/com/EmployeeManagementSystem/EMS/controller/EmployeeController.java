package com.EmployeeManagementSystem.EMS.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.EmployeeDTO;
import com.EmployeeManagementSystem.EMS.dto.EmployeeDetailsDTO;
import com.EmployeeManagementSystem.EMS.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ems/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/add")
	public ResponseEntity<?> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws NoDataFoundException {
		employeeService.addEmployee(employeeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Emplyoee created");
	}

	@PatchMapping("/update/{employeeCode}")
	public ResponseEntity<?> updateEmployeeSalary(@PathVariable long employeeCode,
			@Valid @RequestBody Map<String, Object> updates) throws NoDataFoundException {
		employeeService.updateEmployee(employeeCode, updates);
		return ResponseEntity.status(HttpStatus.OK).body("Emplyoee Updated");
	}

	@PatchMapping("/deactivate/{employeeCode}")
	public ResponseEntity<?> deactivateEmployee(@PathVariable long employeeCode) throws NoDataFoundException {
		employeeService.deactivate(employeeCode);
		return ResponseEntity.status(HttpStatus.OK).body("Employee deactivated sucessfully");
	}

	@GetMapping("/EmployeeDetails/{employeeCode}")
	public ResponseEntity<EmployeeDetailsDTO> EmployeeDetails(@PathVariable long employeeCode)
			throws NoDataFoundException {
		EmployeeDetailsDTO fetchEmployeeDetails = employeeService.fetchEmployeeDetails(employeeCode);
		return new ResponseEntity<>(fetchEmployeeDetails, HttpStatus.OK);
	}
}
