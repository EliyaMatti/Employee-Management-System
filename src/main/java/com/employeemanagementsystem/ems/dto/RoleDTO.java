package com.employeemanagementsystem.ems.dto;

import com.employeemanagementsystem.ems.entity.RoleType;
import lombok.Data;

@Data
public class RoleDTO {
  private Long id;
  private RoleType name;
}
