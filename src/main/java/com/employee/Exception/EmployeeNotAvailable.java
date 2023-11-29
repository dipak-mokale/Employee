package com.employee.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class EmployeeNotAvailable extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public EmployeeNotAvailable(String message) {
		super(message);
	}
	
}
