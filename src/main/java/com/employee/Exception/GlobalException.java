package com.employee.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(EmployeeNotAvailable.class)
	public String handleException(EmployeeNotAvailable ex) {
		return ex.getMessage();
	}
}
