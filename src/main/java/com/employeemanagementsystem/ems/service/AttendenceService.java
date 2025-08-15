package com.employeemanagementsystem.ems.service;

import com.employeemanagementsystem.ems.dto.AttendanceDTO;
import com.employeemanagementsystem.ems.expection.AlreadyCheckedInException;
import com.employeemanagementsystem.ems.expection.NoDataFoundException;
import java.util.List;

public interface AttendenceService {

  void markAttendence(long employeeCode) throws NoDataFoundException, AlreadyCheckedInException;

  void completeAttendence(long employeeCode) throws NoDataFoundException;

  List<AttendanceDTO> AttendenceList(long employeeCode) throws NoDataFoundException;
}
