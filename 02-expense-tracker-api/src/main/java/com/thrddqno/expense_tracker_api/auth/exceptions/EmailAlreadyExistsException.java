package com.thrddqno.expense_tracker_api.auth.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(force = true)
@Data
public class EmailAlreadyExistsException extends RuntimeException {
	private final String errorCode;
	private final HttpStatus httpStatus;
	public EmailAlreadyExistsException(String message, String errorCode, HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}
}
