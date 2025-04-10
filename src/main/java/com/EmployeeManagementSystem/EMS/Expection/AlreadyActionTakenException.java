package com.EmployeeManagementSystem.EMS.Expection;

public class AlreadyActionTakenException  extends Exception{

	public AlreadyActionTakenException(String message) {
		super(message);
	}
}
