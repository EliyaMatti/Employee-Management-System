package com.EmployeeManagementSystem.EMS.serviceimpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.EMS.Entity.Employee;
import com.EmployeeManagementSystem.EMS.Entity.LeaveRequest;
import com.EmployeeManagementSystem.EMS.Entity.LeaveStatus;
import com.EmployeeManagementSystem.EMS.Expection.AlreadyActionTakenException;
import com.EmployeeManagementSystem.EMS.Expection.InsufficientLeaveException;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.LeaveActionDto;
import com.EmployeeManagementSystem.EMS.dto.LeaveRequestDTO;
import com.EmployeeManagementSystem.EMS.repository.EmployeeRepository;
import com.EmployeeManagementSystem.EMS.repository.LeavesRespository;
import com.EmployeeManagementSystem.EMS.service.LeaveService;

import jakarta.transaction.Transactional;

@Service
public class LeaveserviceImpl implements LeaveService {

	private LeavesRespository leavesRespository;
	private EmployeeRepository employeeRepository;
	
	

	public LeaveserviceImpl(LeavesRespository leavesRespository, EmployeeRepository employeeRepository) {
		this.leavesRespository = leavesRespository;
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	@Override
	public void applyLeave(LeaveRequestDTO leaveRequestDTO) throws NoDataFoundException, InsufficientLeaveException {
		Employee employee = employeeRepository.findById(leaveRequestDTO.getEmployeeId()).orElseThrow(
				() -> new NoDataFoundException("No Employee Found with id : " + leaveRequestDTO.getEmployeeId()));
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setEmployee(employee);

//		if (leaveRequestDTO.getLeaveType() == null) {
//		    throw new IllegalArgumentException("Leave type is required");
//		}
		List<LeaveRequest> leaveRequestlist = leavesRespository.findByEmployee(leaveRequestDTO.getEmployeeId());

//		leaveRequestlist.ifPresent();

		if (leaveRequestlist.isEmpty() || leaveRequestlist.size()==0 ) {
			leaveRequest.setLeave_balance(4);
		}else {
			
			Optional<LeaveRequest> leastLeaveBalance = leaveRequestlist.stream()
					.sorted(Comparator.comparingInt(LeaveRequest::getLeave_balance)).findFirst();
			if (leastLeaveBalance.get().getLeave_balance() == 0) {
				throw new InsufficientLeaveException("NO leaves left ");
			} else {
				leaveRequest.setLeave_balance(leastLeaveBalance.get().getLeave_balance() - 1);
			}
		}

		leaveRequest.setLeaveType(leaveRequestDTO.getLeaveType().name());
		leaveRequest.setReason(leaveRequestDTO.getReason());
		leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
		leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
		leaveRequest.setStatus(LeaveStatus.PENDING.toString());
		leavesRespository.saveAndFlush(leaveRequest);
	}

	@Override
	public void takeAction(LeaveActionDto leaveActionDto) throws NoDataFoundException ,AlreadyActionTakenException {
		Employee employee = employeeRepository.findById(leaveActionDto.getEmployeeId()).orElseThrow(
				() -> new NoDataFoundException("No Employee Found with id : " + leaveActionDto.getEmployeeId()));
		
		List<LeaveRequest> leaveList = leavesRespository.findByEmployee(leaveActionDto.getEmployeeId());
		
		System.out.println(leaveList.toString());
		
			LeaveRequest latestRequest = leaveList.stream()
				    .max(Comparator.comparing(LeaveRequest::getStartDate))
				    .orElseThrow(() -> new NoDataFoundException("No leave requests found for employee"));
//			System.out.println(latestRequest.getClass());
			if(!latestRequest.getStatus().equals("PENDING")) {
				throw new AlreadyActionTakenException("Already Action Taken on leave request");
			}
			
			latestRequest.setStatus(leaveActionDto.getLeaveStatus().toString());
			leavesRespository.save(latestRequest);
		
		
		
		
	}

}
