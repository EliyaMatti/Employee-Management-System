package com.EmployeeManagementSystem.EMS.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AttendanceDTO {
  private Long id;
  private Long employeeId;
  private LocalDate attandancedate;
  private LocalDateTime clockIn;
  //    public AttendanceDTO(Long employeeId, LocalDate attandancedate, LocalDateTime clockIn,
  // LocalDateTime clockOut,Double totalHours) {
  //		super();
  //		this.employeeId = employeeId;
  //		this.attandancedate = attandancedate;
  //		this.clockIn = clockIn;
  //		this.clockOut = clockOut;
  ////		this.totalHours = totalHours;
  //	}
  //	public AttendanceDTO(long employeeCode, LocalDate attendanceDate, LocalDateTime clockIn2,
  // LocalDateTime clockOut2,
  //			BigDecimal totalHours2) {
  //		// TODO Auto-generated constructor stub
  //	}
  //	public AttendanceDTO(long employeeCode, LocalDate attendanceDate, LocalDateTime clockIn2,
  // LocalDateTime clockOut2) {
  //		// TODO Auto-generated constructor stub
  //	}
  private LocalDateTime clockOut;
  private BigDecimal totalHours;

  public AttendanceDTO(
      Long employeeId,
      LocalDate attandancedate,
      LocalDateTime clockIn,
      LocalDateTime clockOut,
      BigDecimal totalHours) {
    super();
    this.employeeId = employeeId;
    this.attandancedate = attandancedate;
    this.clockIn = clockIn;
    this.clockOut = clockOut;
    this.totalHours = totalHours;
  }
}
