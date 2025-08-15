package com.employeemanagementsystem.ems.dto;

import com.employeemanagementsystem.ems.entity.LeaveStatus;
import lombok.Data;

@Data
public class LeaveActionDto {

  LeaveStatus leaveStatus;
  long employeeId;
}
