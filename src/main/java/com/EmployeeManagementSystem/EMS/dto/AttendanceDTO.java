package com.EmployeeManagementSystem.EMS.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendanceDTO {
    private Long id;
    private Long employeeId;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
    private Double totalHours;
}
