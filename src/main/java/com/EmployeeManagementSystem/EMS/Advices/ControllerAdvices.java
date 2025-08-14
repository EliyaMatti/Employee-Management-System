package com.EmployeeManagementSystem.EMS.Advices;

// import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.EmployeeManagementSystem.EMS.Expection.AlreadyActionTakenException;
import com.EmployeeManagementSystem.EMS.Expection.AlreadyCheckedInException;
import com.EmployeeManagementSystem.EMS.Expection.InsufficientLeaveException;
import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

  @ExceptionHandler(RuntimeException.class)
  ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException ex) {
    ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    ex.printStackTrace();
    return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NoDataFoundException.class)
  ResponseEntity<ExceptionDto> handleNotFoundException(NoDataFoundException ex) {
    ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.NOT_FOUND, ex.getMessage());
    return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AlreadyCheckedInException.class)
  ResponseEntity<ExceptionDto> handleInvalidException(AlreadyCheckedInException ex) {
    ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.CONFLICT, ex.getMessage());
    return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(InsufficientLeaveException.class)
  ResponseEntity<ExceptionDto> handleInsufficientException(InsufficientLeaveException ex) {
    ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AlreadyActionTakenException.class)
  ResponseEntity<ExceptionDto> handleForbiddenException(AlreadyActionTakenException ex) {
    ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.FORBIDDEN, ex.getMessage());
    return new ResponseEntity<>(exceptionDto, HttpStatus.FORBIDDEN);
  }
}
