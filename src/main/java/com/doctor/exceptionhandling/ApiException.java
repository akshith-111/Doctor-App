package com.doctor.exceptionhandling;

import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiException extends RuntimeException{
	private String errorCode;
	
	public ApiException(String message,String errorCode) {
		super(message);
		this.errorCode=errorCode;
	}

}
