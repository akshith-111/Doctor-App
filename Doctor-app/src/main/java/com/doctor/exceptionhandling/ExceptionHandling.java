package com.doctor.exceptionhandling;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundHandler(ResourceNotFoundException e) {

		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMessage> noSuchElementException(NoSuchElementException e) {

		ErrorMessage errorMessage = new ErrorMessage("No value Present", "NO_SUCH_ELEMENT");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorMessage> nullPointerExceptionHandler(NullPointerException e) {

		ErrorMessage errorMessage = new ErrorMessage("Cannot do Operations on Null", "NULL_POINTER_EXCEPTION");
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorMessage);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorMessage> noResourceFoundException(NoResourceFoundException e) {

		ErrorMessage errorMessage = new ErrorMessage("No path found " + e.getResourcePath(), e.getTitleMessageCode());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> sqlIntegrityConstraintViolationExceptionHandler(Exception e) {

		ErrorMessage errorMessage = new ErrorMessage("Duplicate Entry", "SQL_ERROR");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

	}

	// Global Exception Handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception e) {

		ErrorMessage errorMessage = new ErrorMessage("Internal server error", "INTERNAL_SERVER_ERROR");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	}

}