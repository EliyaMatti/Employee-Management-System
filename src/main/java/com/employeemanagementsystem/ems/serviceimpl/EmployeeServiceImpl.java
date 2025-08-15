package com.employeemanagementsystem.ems.serviceimpl;

import com.employeemanagementsystem.ems.dto.DepartmentDTO;
import com.employeemanagementsystem.ems.dto.EmployeeDTO;
import com.employeemanagementsystem.ems.dto.EmployeeDetailsDTO;
import com.employeemanagementsystem.ems.entity.Department;
import com.employeemanagementsystem.ems.entity.Employee;
import com.employeemanagementsystem.ems.expection.NoDataFoundException;
import com.employeemanagementsystem.ems.repository.DepartmentRepository;
import com.employeemanagementsystem.ems.repository.EmployeeRepository;
import com.employeemanagementsystem.ems.service.EmployeeService;
import jakarta.transaction.Transactional;
import java.math.BigInteger;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;
  private DepartmentRepository departmentRepository;

  public EmployeeServiceImpl(
      EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
    this.employeeRepository = employeeRepository;
    this.departmentRepository = departmentRepository;
  }

  @Transactional
  @Override
  public void addEmployee(EmployeeDTO employeeDTO) throws NoDataFoundException {
    Employee employee = new Employee();
    employee.setName(employeeDTO.getName());
    BigInteger checklastemployeeId = employeeRepository.checklastemployeeId();
    if (checklastemployeeId == null) {
      employee.setEmployeeCode(1001l);
    } else {
      long empcode = checklastemployeeId.longValue();
      employee.setEmployeeCode(++empcode);
    }

    Department department =
        departmentRepository
            .findById(employeeDTO.getDepartmentId())
            .orElseThrow(
                () ->
                    new NoDataFoundException(
                        "No Departmnent found with id :" + employeeDTO.getDepartmentId()));
    employee.setDepartment(department);
    employee.setDesignation(employeeDTO.getDesignation());
    employee.setEmail(employeeDTO.getEmail());
    employee.setJoiningDate(employeeDTO.getJoiningDate());
    employee.setSalary(employeeDTO.getSalary());
    employee.setPhone(employeeDTO.getPhone());
    employeeRepository.saveAndFlush(employee);
  }

  @Transactional
  @Override
  public void updateEmployee(long id, Map<String, Object> updates) throws NoDataFoundException {
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new NoDataFoundException("No Employee Found with id : " + id));

    updates.forEach(
        (key, value) -> {
          if (key.equals("email")) employee.setName((String) value);
          if (key.equals("phone")) employee.setPhone((String) value);
          if (key.equals("designation")) employee.setDesignation((String) value);
          if (key.equals("salary")) employee.setSalary((Double) value);
        });
    employeeRepository.save(employee);
  }

  @Transactional
  @Override
  public void deactivate(long id) throws NoDataFoundException {

    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new NoDataFoundException("No Employee Found with id : " + id));

    employee.setIsActive(false);
    employeeRepository.saveAndFlush(employee);
  }

  @Override
  public EmployeeDetailsDTO fetchEmployeeDetails(long employeeCode) throws NoDataFoundException {
    Employee employee =
        employeeRepository
            .findById(employeeCode)
            .orElseThrow(
                () -> new NoDataFoundException("No Employee Found with id : " + employeeCode));
    EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
    employeeDetailsDTO.setName(employee.getName());
    employeeDetailsDTO.setEmail(employee.getEmail());
    employeeDetailsDTO.setPhone(employee.getPhone());
    employeeDetailsDTO.setJoiningDate(employee.getJoiningDate());
    employeeDetailsDTO.setSalary(employee.getSalary());
    Department department =
        departmentRepository
            .findById(employee.getDepartment().getId())
            .orElseThrow(
                () ->
                    new NoDataFoundException(
                        "No Department found with id :" + employee.getDepartment().getId()));

    // Convert to DTOs
    DepartmentDTO departmentDTO = new DepartmentDTO();
    departmentDTO.setId(department.getId());
    departmentDTO.setName(department.getName());
    employeeDetailsDTO.setDepartment(departmentDTO);
    return employeeDetailsDTO;
  }
}
