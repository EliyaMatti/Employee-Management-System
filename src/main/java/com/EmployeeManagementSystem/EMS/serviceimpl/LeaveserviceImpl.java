package com.EmployeeManagementSystem.EMS.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.EMS.Entity.Employee;
import com.EmployeeManagementSystem.EMS.Entity.LeaveRequest;
import com.EmployeeManagementSystem.EMS.Entity.LeaveStatus;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.LeaveRequestDTO;
import com.EmployeeManagementSystem.EMS.repository.EmployeeRepository;
import com.EmployeeManagementSystem.EMS.repository.LeavesRespository;
import com.EmployeeManagementSystem.EMS.service.LeaveService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class LeaveserviceImpl implements LeaveService {

	@Autowired
	LeavesRespository leavesRespository;
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	@Override
	public void applyLeave(LeaveRequestDTO leaveRequestDTO) throws NoDataFoundException {
		Employee employee = employeeRepository.findById(leaveRequestDTO.getEmployeeId())
				.orElseThrow(() -> new NoDataFoundException("No Employee Found with id : " + leaveRequestDTO.getEmployeeId()));
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setEmployee(employee);
		
//		if (leaveRequestDTO.getLeaveType() == null) {
//		    throw new IllegalArgumentException("Leave type is required");
//		}
		
		leaveRequest.setLeaveType(leaveRequestDTO.getLeaveType().name());
		leaveRequest.setReason(leaveRequestDTO.getReason());
		leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
		leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
		leaveRequest.setStatus(LeaveStatus.PENDING.toString());
		leavesRespository.saveAndFlush(leaveRequest);
	}

	@Override
	public void takeAction(@Valid LeaveStatus leaveStatus ,long employeeId) {
		
		
	}

}
