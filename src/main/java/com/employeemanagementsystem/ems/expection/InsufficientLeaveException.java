package com.employeemanagementsystem.ems.expection;

public class InsufficientLeaveException extends Exception {

  public InsufficientLeaveException(String message) {
    super(message);
  }
}
