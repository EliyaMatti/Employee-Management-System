package com.employeemanagementsystem.ems.expection;

public class AlreadyCheckedInException extends Exception {
  public AlreadyCheckedInException(String message) {
    super(message);
  }
}
