package com.EmployeeManagementSystem.EMS.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.EMS.Entity.Attendance;
import com.EmployeeManagementSystem.EMS.Entity.Employee;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.repository.AttendanceRepository;
import com.EmployeeManagementSystem.EMS.repository.EmployeeRepository;
import com.EmployeeManagementSystem.EMS.service.AttendenceService;

import jakarta.transaction.Transactional;

@Service
public class AttendenceServiceImpl  implements AttendenceService{

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	AttendanceRepository attendanceRepository;
	@Transactional
	@Override
	public void markAttendence(long employeeCode) throws NoDataFoundException {
		Employee employee = employeeRepository.findById(employeeCode)
				.orElseThrow(() -> new NoDataFoundException("No Employee Found with id : " + employeeCode));
		Attendance attendance = new Attendance();
//		Date date = new Date();
		LocalDate localDate = LocalDate.now() ;
		attendance.setAttendanceDate(localDate);
		attendance.setEmployee(employee);
		LocalDateTime localDateTime = LocalDateTime.now();
		attendance.setClockIn(localDateTime);
		attendanceRepository.saveAndFlush(attendance);
	}

}
