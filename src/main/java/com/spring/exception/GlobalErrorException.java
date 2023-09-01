package com.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.model.ApiResponse;

@ControllerAdvice

public class GlobalErrorException extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = WorkNotFoundException.class)

	public ResponseEntity<ApiResponse> handleProductNotFoundException(WorkNotFoundException ex, WebRequest request) {

		ApiResponse obj = new ApiResponse();

		obj.setMessage(ex.getMessage());

		obj.setStatusCode("404");

		
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = EmployeeNotFoundException.class)

	public ResponseEntity<ApiResponse> handleTransactionException(EmployeeNotFoundException ex, WebRequest request) {

		ApiResponse obj = new ApiResponse();

		obj.setMessage(ex.getMessage());

		obj.setStatusCode("404");

		

		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(value =ClientNotFoundException.class)

	public ResponseEntity<ApiResponse> handleTransactionException(ClientNotFoundException ex, WebRequest request) {

		ApiResponse obj = new ApiResponse();

		obj.setMessage(ex.getMessage());

		obj.setStatusCode("404");

		
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(value =WorkException.class)
	public ResponseEntity<ApiResponse> handleTransactionException(WorkException ex, WebRequest request) {

		ApiResponse obj = new ApiResponse();

		obj.setMessage(ex.getMessage());

		obj.setStatusCode("404");

		

		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);

	}
}
