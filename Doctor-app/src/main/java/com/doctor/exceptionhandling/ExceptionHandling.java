package com.doctor.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundHandler(ResourceNotFoundException e ){
		
		ErrorMessage errorMessage=new ErrorMessage(e.getMessage(),e.getErrorCode());
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		
		}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception e){
		
		ErrorMessage errorMessage=new ErrorMessage("Inernal server error","INTERNAL_SERVER_ERROR");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	}
}