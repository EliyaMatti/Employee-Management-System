package com.EmployeeManagementSystem.EMS.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PayrollDTO {
    private Long id;
    private Long employeeId;
    private Double baseSalary;
    private Double bonus;
    private Double tax;
    private Double totalSalary;
    private LocalDate paymentDate;
}
