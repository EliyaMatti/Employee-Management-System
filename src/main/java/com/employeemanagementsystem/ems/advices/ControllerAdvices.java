package com.employeemanagementsystem.ems.advices;

// import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.employeemanagementsystem.ems.dto.ExceptionDto;
import com.employeemanagementsystem.ems.expection.AlreadyActionTakenException;
import com.employeemanagementsystem.ems.expection.AlreadyCheckedInException;
import com.employeemanagementsystem.ems.expection.InsufficientLeaveException;
import com.employeemanagementsystem.ems.expection.NoDataFoundException;
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
