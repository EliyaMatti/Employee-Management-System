package com.EmployeeManagementSystem.EMS.serviceimpl;

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
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LeaveserviceImpl implements LeaveService {

  private LeavesRespository leavesRespository;
  private EmployeeRepository employeeRepository;

  public LeaveserviceImpl(
      LeavesRespository leavesRespository, EmployeeRepository employeeRepository) {
    this.leavesRespository = leavesRespository;
    this.employeeRepository = employeeRepository;
  }

  @Transactional
  @Override
  public void applyLeave(LeaveRequestDTO leaveRequestDTO)
      throws NoDataFoundException, InsufficientLeaveException {
    Employee employee =
        employeeRepository
            .findById(leaveRequestDTO.getEmployeeId())
            .orElseThrow(
                () ->
                    new NoDataFoundException(
                        "No Employee Found with id : " + leaveRequestDTO.getEmployeeId()));
    LeaveRequest leaveRequest = new LeaveRequest();
    leaveRequest.setEmployee(employee);

    List<LeaveRequest> leaveRequestlist =
        leavesRespository.findByEmployee(leaveRequestDTO.getEmployeeId());

    long appliedleaves =
        ChronoUnit.DAYS.between(leaveRequestDTO.getStartDate(), leaveRequestDTO.getEndDate()) + 1;
    if (leaveRequestlist.isEmpty() || leaveRequestlist.size() == 0) {
      leaveRequest.setLeave_balance(10 - (int) appliedleaves);
    } else {

      Optional<LeaveRequest> leastLeaveBalance =
          leaveRequestlist.stream()
              .sorted(Comparator.comparingInt(LeaveRequest::getLeave_balance))
              .findFirst();
      if (leastLeaveBalance.get().getLeave_balance() == 0) {
        throw new InsufficientLeaveException("NO leaves left ");
      } else {
        if (leastLeaveBalance.get().getLeave_balance() < (int) appliedleaves) {
          throw new InsufficientLeaveException(
              " leaves left are "
                  + leastLeaveBalance.get().getLeave_balance()
                  + " but applied leaves are "
                  + appliedleaves);
        }
        leaveRequest.setLeave_balance(
            leastLeaveBalance.get().getLeave_balance() - (int) appliedleaves);
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
  public void takeAction(LeaveActionDto leaveActionDto)
      throws NoDataFoundException, AlreadyActionTakenException {
    employeeRepository
        .findById(leaveActionDto.getEmployeeId())
        .orElseThrow(
            () ->
                new NoDataFoundException(
                    "No Employee Found with id : " + leaveActionDto.getEmployeeId()));
    List<LeaveRequest> leaveList = leavesRespository.findByEmployee(leaveActionDto.getEmployeeId());
    LeaveRequest latestRequest =
        leaveList.stream()
            .filter(t -> t.getStatus().equalsIgnoreCase("PENDING"))
            .max(Comparator.comparing(LeaveRequest::getStartDate))
            .orElseThrow(
                () -> new AlreadyActionTakenException("Already Action Taken on leave request"));
    latestRequest.setStatus(leaveActionDto.getLeaveStatus().toString());
    leavesRespository.save(latestRequest);
  }

  @Override
  public Integer leaveBalance(long employeeId) throws NoDataFoundException {
    employeeRepository
        .findById(employeeId)
        .orElseThrow(() -> new NoDataFoundException("No Employee Found with id : " + employeeId));
    Integer leaveBalance = leavesRespository.leaveBalance(employeeId);
    System.out.println(leaveBalance);
    //        LeaveRequest leaveRequest= leaveList.stream()
    //                .filter(lr -> lr.getEndDate().getYear() == LocalDate.now().getYear())
    //                .max(Comparator.comparing(LeaveRequest::getEndDate))
    //                .orElseThrow(() -> new NoDataFoundException("No data found"));
    return leaveBalance;
  }
}
