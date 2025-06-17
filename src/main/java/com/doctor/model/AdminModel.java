package com.doctor.model;


import org.hibernate.validator.constraints.Length;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel {

	
	@Length(max = 40,min = 4,message = "Contact Should be 10 Digits")
	private String adminName;
	
	@Length(max = 10,min = 10,message = "Contact Should be 10 Digits")
	private String contactNumber;
	@Email
	private String email;
	@Length(max = 50,min = 4,message = "Contact Should be 10 Digits")
	private String password;
}
