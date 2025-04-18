package com.EmployeeManagementSystem.EMS.dto;

import java.time.LocalDate;

import com.EmployeeManagementSystem.EMS.Entity.LeaveType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeaveRequestDTO {
    private Long id;
    private Long employeeId;
    @JsonProperty("leaveType")
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
