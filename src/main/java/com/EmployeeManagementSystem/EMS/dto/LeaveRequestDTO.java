package com.EmployeeManagementSystem.EMS.dto;

import java.time.LocalDate;

import com.EmployeeManagementSystem.EMS.Entity.LeaveType;

import lombok.Data;

@Data
public class LeaveRequestDTO {
    private Long id;
    private Long employeeId;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isApproved;
}
