package com.EmployeeManagementSystem.EMS.serviceimpl;

import com.EmployeeManagementSystem.EMS.Entity.Attendance;
import com.EmployeeManagementSystem.EMS.Entity.Employee;
import com.EmployeeManagementSystem.EMS.Expection.AlreadyCheckedInException;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.AttendanceDTO;
import com.EmployeeManagementSystem.EMS.repository.AttendanceRepository;
import com.EmployeeManagementSystem.EMS.repository.EmployeeRepository;
import com.EmployeeManagementSystem.EMS.service.AttendenceService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AttendenceServiceImpl implements AttendenceService {

  EmployeeRepository employeeRepository;
  AttendanceRepository attendanceRepository;
  ModelMapper modelMapper;

  public AttendenceServiceImpl(
      EmployeeRepository employeeRepository,
      AttendanceRepository attendanceRepository,
      ModelMapper modelMapper) {
    this.employeeRepository = employeeRepository;
    this.attendanceRepository = attendanceRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional
  @Override
  public void markAttendence(long employeeCode)
      throws NoDataFoundException, AlreadyCheckedInException {
    Employee employee =
        employeeRepository
            .findById(employeeCode)
            .orElseThrow(
                () -> new NoDataFoundException("No Employee Found with id : " + employeeCode));
    Attendance attendance = new Attendance();
    LocalDate today = LocalDate.now();
    boolean exists = attendanceRepository.existsByEmployeeAndAttendanceDate(employee, today);
    if (exists) throw new AlreadyCheckedInException("Already attadance marked");
    attendance.setAttendanceDate(today);
    attendance.setEmployee(employee);
    LocalDateTime localDateTime = LocalDateTime.now();
    attendance.setClockIn(localDateTime);
    attendanceRepository.save(attendance);
  }

  @Transactional
  @Override
  public void completeAttendence(long employeeCode) throws NoDataFoundException {
    Employee employee =
        employeeRepository
            .findById(employeeCode)
            .orElseThrow(
                () -> new NoDataFoundException("No Employee Found with id : " + employeeCode));
    LocalDate today = LocalDate.now();
    Attendance attendance =
        attendanceRepository
            .findByEmployeeAndAttendanceDate(employee, today)
            .orElseThrow(
                () ->
                    new NoDataFoundException(
                        "No attendencae record found for employee  : " + employeeCode));
    LocalDateTime CheckOuttime = LocalDateTime.now();
    attendance.setClockOut(CheckOuttime);
    Duration duration = Duration.between(attendance.getClockIn(), CheckOuttime);
    long seconds = duration.getSeconds();
    BigDecimal totalHours =
        BigDecimal.valueOf(seconds).divide(BigDecimal.valueOf(3600), 2, RoundingMode.HALF_UP);
    attendance.setTotalHours(totalHours);
    attendanceRepository.save(attendance);
  }

  @Override
  public List<AttendanceDTO> AttendenceList(long employeeCode) throws NoDataFoundException {
    Employee employee =
        employeeRepository
            .findById(employeeCode)
            .orElseThrow(
                () -> new NoDataFoundException("No Employee Found with id : " + employeeCode));
    List<Attendance> attendecelist = attendanceRepository.findAllByEmployee(employee);
    List<AttendanceDTO> list =
        attendecelist.stream()
            .map(
                att ->
                    new AttendanceDTO(
                        att.getId(),
                        att.getEmployee().getEmployeeCode(),
                        att.getAttendanceDate(),
                        att.getClockIn(),
                        att.getClockOut(),
                        att.getTotalHours()))
            .collect(Collectors.toList());
    return list;
  }
}
