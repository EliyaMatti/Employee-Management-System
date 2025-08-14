package com.EmployeeManagementSystem.EMS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EmployeeDTO {
  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Phone number is required")
  private String phone;

  @NotNull(message = "Department ID is required")
  private Long departmentId;

  @NotBlank(message = "Designation is required")
  private String designation;

  @NotNull(message = "Salary is required")
  @Positive(message = "Salary must be greater than zero")
  private Double salary;

  private LocalDate joiningDate;
}
