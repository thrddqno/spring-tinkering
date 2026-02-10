package com.thrddqno.expense_tracker_api.common.exceptions;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.thrddqno.expense_tracker_api.auth.exceptions.EmailAlreadyExistsException;
import com.thrddqno.expense_tracker_api.auth.exceptions.InvalidCredentialsException;
import com.thrddqno.expense_tracker_api.common.exceptions.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		 error.setErrorCode(ex.getErrorCode());
	     error.setMessage(ex.getMessage());
	     error.setStatus(ex.getHttpStatus().value());
	     error.setTimestamp(Instant.now());
	     error.setPath(request.getRequestURI());
	     return new ResponseEntity<>(error, ex.getHttpStatus());
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		 error.setErrorCode(ex.getErrorCode());
	     error.setMessage(ex.getMessage());
	     error.setStatus(ex.getHttpStatus().value());
	     error.setTimestamp(Instant.now());
	     error.setPath(request.getRequestURI());
	     return new ResponseEntity<>(error, ex.getHttpStatus());
	}
}
