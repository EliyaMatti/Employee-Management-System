package com.EmployeeManagementSystem.EMS.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.EMS.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "select employee_code from employee_management.employees order by employee_code desc LIMIT 1;",nativeQuery = true)
	BigInteger checklastemployeeId();

}
