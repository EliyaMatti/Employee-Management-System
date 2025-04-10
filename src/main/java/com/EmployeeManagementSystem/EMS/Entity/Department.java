package com.EmployeeManagementSystem.EMS.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "departments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Department {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}
