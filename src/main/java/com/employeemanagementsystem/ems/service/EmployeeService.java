package com.employeemanagementsystem.ems.service;

import com.employeemanagementsystem.ems.dto.EmployeeDTO;
import com.employeemanagementsystem.ems.dto.EmployeeDetailsDTO;
import com.employeemanagementsystem.ems.expection.NoDataFoundException;
import java.util.Map;

public interface EmployeeService {

  public void addEmployee(EmployeeDTO employeeDTO) throws NoDataFoundException;

  public void updateEmployee(long id, Map<String, Object> updates) throws NoDataFoundException;

  public void deactivate(long id) throws NoDataFoundException;

  public EmployeeDetailsDTO fetchEmployeeDetails(long employeeCode) throws NoDataFoundException;
}
