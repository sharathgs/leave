package com.leavemanagement.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	  @ExceptionHandler(InvalidLoginException.class) public
	  ResponseEntity<ErrorResponse> globalExceptionHandler(InvalidLoginException
	  exception,WebRequest request) { ErrorResponse errorResponse = new
	  ErrorResponse(LocalDate.now(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
	  
	  return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	  
	  }
	 

	@ExceptionHandler(LeaveException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(LeaveException exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setTimestamp(LocalDate.now());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientLeaveException.class)
	public ResponseEntity<ErrorResponse> insufficientLeaveExceptionHandler(InsufficientLeaveException exception,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), exception.getMessage(),
				HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(EmployeeDoesNotExistException.class)
	public ResponseEntity<ErrorResponse> employeeDoesNotExistExceptionHandler(EmployeeDoesNotExistException exception,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), exception.getMessage(),
				HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<ErrorResponse> invalidDateExceptionHandler(InvalidDateException exception,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), exception.getMessage(),
				HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(AlreadyApplyLeaveException.class)
	public ResponseEntity<ErrorResponse> alreadyApplyLeaveExceptionHandler(AlreadyApplyLeaveException exception,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), exception.getMessage(),
				HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

}
