package com.EmployeeManagementSystem.EMS.dto;

import lombok.Data;

@Data
public class SalaryStructureDTO {
    private Long id;
    private Long employeeId;
    private Double baseSalary;
    private Double bonus;
    private Double taxRate;
}
