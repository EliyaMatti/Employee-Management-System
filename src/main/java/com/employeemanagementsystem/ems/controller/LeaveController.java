package com.employeemanagementsystem.ems.controller;

import com.employeemanagementsystem.ems.dto.LeaveActionDto;
import com.employeemanagementsystem.ems.dto.LeaveRequestDTO;
import com.employeemanagementsystem.ems.expection.AlreadyActionTakenException;
import com.employeemanagementsystem.ems.expection.InsufficientLeaveException;
import com.employeemanagementsystem.ems.expection.NoDataFoundException;
import com.employeemanagementsystem.ems.service.LeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ems/employee")
public class LeaveController {

  private LeaveService leaveService;

  public LeaveController(LeaveService leaveService) {
    this.leaveService = leaveService;
  }

  @PostMapping("/leaves/applyLeave")
  public ResponseEntity<?> ApplyLeave(@RequestBody LeaveRequestDTO leaveRequestDTO)
      throws NoDataFoundException, InsufficientLeaveException {
    leaveService.applyLeave(leaveRequestDTO);
    return ResponseEntity.status(HttpStatus.OK).body("Leave Applied");
  }

  @PutMapping("/leaves/Leaveaction")
  public ResponseEntity<?> leaveaction(@RequestBody LeaveActionDto leaveActionDto)
      throws NoDataFoundException, AlreadyActionTakenException {
    leaveService.takeAction(leaveActionDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body("leave " + leaveActionDto.getLeaveStatus().toString());
  }

  @GetMapping("/leaves/leavebalance/{employeeId}")
  public ResponseEntity<?> checkBalance(@PathVariable long employeeId) throws NoDataFoundException {
    Integer leaveBalance = leaveService.leaveBalance(employeeId);
    return ResponseEntity.status(HttpStatus.OK).body("leaves Left " + leaveBalance.toString());
  }
}
