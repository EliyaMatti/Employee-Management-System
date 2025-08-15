package com.employeemanagementsystem.ems.expection;

public class AlreadyActionTakenException extends Exception {

  public AlreadyActionTakenException(String message) {
    super(message);
  }
}
