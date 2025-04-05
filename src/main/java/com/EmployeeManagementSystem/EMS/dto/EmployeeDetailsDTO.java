package com.EmployeeManagementSystem.EMS.dto;

import java.time.LocalDate;

import com.EmployeeManagementSystem.EMS.Entity.Department;

import lombok.Data;

@Data
public class EmployeeDetailsDTO {
	private String name;
	private String email;
	private String phone;
	private String designation;
	private Double salary;
	private LocalDate joiningDate;
	private DepartmentDTO department;
	
}
