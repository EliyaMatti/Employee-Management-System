package com.EmployeeManagementSystem.EMS.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    private String phone;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @Column(nullable = false)
//    private String designation;
//
//    @Column(nullable = false)
//    private Double salary;
//
//    private LocalDate joiningDate;
//
//    private Boolean isActive = true;


    @Id
    @Column(name = "employee_code", length = 50)
    private long employeeCode;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    @Column(nullable = false)
    private String designation;
    @Column(nullable = false)
    private Double salary;

    private LocalDate joiningDate;

    private Boolean isActive = true;
}
