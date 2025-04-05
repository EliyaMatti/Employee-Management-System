package com.EmployeeManagementSystem.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.EMS.Entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
