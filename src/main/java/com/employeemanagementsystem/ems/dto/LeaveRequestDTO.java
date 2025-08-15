package com.employeemanagementsystem.ems.dto;

import com.employeemanagementsystem.ems.entity.LeaveType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
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
