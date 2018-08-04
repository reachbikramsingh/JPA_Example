package com.bikram.springdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bikram.springdemo.exception.CustomerErrorResponse;
import com.bikram.springdemo.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handelException(CustomerNotFoundException exce) {
		CustomerErrorResponse errorResponse = new CustomerErrorResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exce.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handelException(Exception exce) {
		CustomerErrorResponse errorResponse = new CustomerErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exce.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}
	// we can follow below code to handle it through parametrized constructor...

	/*
	 * @ExceptionHandler public ResponseEntity<CustomerErrorResponse>
	 * handelException(Exception exce) { CustomerErrorResponse errorResponse = new
	 * CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),
	 * exce.getMessage(),System.currentTimeMillis()); return new
	 * ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */

}
