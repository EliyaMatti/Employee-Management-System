package com.EmployeeManagementSystem.EMS.dto;

import com.EmployeeManagementSystem.EMS.Entity.RoleType;

import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private RoleType name;
}
