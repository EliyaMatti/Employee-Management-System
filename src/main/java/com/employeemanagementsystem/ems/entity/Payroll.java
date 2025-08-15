package com.employeemanagementsystem.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "payroll",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_code", "month"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "employee_code", nullable = false)
  private Employee employee;

  @Column(length = 7) // e.g. "2024-04"
  private String month;

  private double baseSalary;

  private double deductions;

  private double bonuses;

  private double netSalary;

  private LocalDate generatedDate;
}
