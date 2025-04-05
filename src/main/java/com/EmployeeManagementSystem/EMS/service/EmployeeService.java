package com.EmployeeManagementSystem.EMS.service;

import java.util.Map;

import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.EmployeeDTO;
import com.EmployeeManagementSystem.EMS.dto.EmployeeDetailsDTO;

public interface EmployeeService {

	public void addEmployee(EmployeeDTO employeeDTO) throws NoDataFoundException;

	public void updateEmployee(long id, Map<String, Object> updates) throws NoDataFoundException;

	public void deactivate(long id) throws NoDataFoundException;

	public EmployeeDetailsDTO fetchEmployeeDetails(long employeeCode)throws NoDataFoundException;
}
