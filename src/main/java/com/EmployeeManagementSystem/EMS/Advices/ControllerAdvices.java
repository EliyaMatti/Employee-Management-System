package com.EmployeeManagementSystem.EMS.Advices;

//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.EmployeeManagementSystem.EMS.Expection.NoDataFoundException;
import com.EmployeeManagementSystem.EMS.dto.ExceptionDto;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException ex){
        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoDataFoundException.class)
    ResponseEntity<ExceptionDto> handleNotFoundException(NoDataFoundException ex){
        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(InvalidDataException.class)
//    ResponseEntity<ExceptionDto> handleInvalidException(InvalidDataException ex){
//        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.BAD_REQUEST, ex.getMessage());
//        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(InsufficientStockException.class)
//    ResponseEntity<ExceptionDto> handleInsufficientException(InsufficientStockException ex){
//        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.BAD_REQUEST, ex.getMessage());
//        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(ResourceAccessForbidden.class)
//    ResponseEntity<ExceptionDto> handleForbiddenException(ResourceAccessForbidden ex){
//        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.FORBIDDEN, ex.getMessage());
//        return new ResponseEntity<>(exceptionDto, HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(PaymentClientException.class)
//    ResponseEntity<ExceptionDto> handlePaymentClientException(PaymentClientException ex){
//        ExceptionDto exceptionDto = new ExceptionDto(HttpStatus.BAD_REQUEST, ex.getMessage());
//        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
//    }
}
