package com.employeemanagementsystem.ems.serviceimpl;

import com.employeemanagementsystem.ems.dto.AttendanceDTO;
import com.employeemanagementsystem.ems.entity.Attendance;
import com.employeemanagementsystem.ems.entity.Employee;
import com.employeemanagementsystem.ems.expection.AlreadyCheckedInException;
import com.employeemanagementsystem.ems.expection.NoDataFoundException;
import com.employeemanagementsystem.ems.repository.AttendanceRepository;
import com.employeemanagementsystem.ems.repository.EmployeeRepository;
import com.employeemanagementsystem.ems.service.AttendenceService;
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
    LocalDateTime checkOuttime = LocalDateTime.now();
    attendance.setClockOut(checkOuttime);
    Duration duration = Duration.between(attendance.getClockIn(), checkOuttime);
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
    return attendecelist.stream()
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
  }
}
