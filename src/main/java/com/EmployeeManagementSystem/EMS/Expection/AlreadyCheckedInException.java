package com.EmployeeManagementSystem.EMS.Expection;

public class AlreadyCheckedInException extends Exception {
  public AlreadyCheckedInException(String message) {
    super(message);
  }
}
