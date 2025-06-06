package com.doctor.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientModel {

	private int patientId;
	
	@Length(max = 30,min = 4,message = "name Should be below 30 Digits")
	private String patientName;
	
	@Length(max = 10,min = 10,message = "Contact Should be 10 Digits")
	private String mobileNo;
	
	@Email
	private String email;
	
	@Length(max = 30,min = 4,message = "Blood Group Should be less than 8 Digits")
	private String bloodGroup;
	
	private String age;
	
	@Length(max = 90,min = 4,message = "address Should be less than 90 Characters")
	private String address;
	
	@Length(max = 30,min = 4,message = "password Should be less than 30 Characters")
	private String password;
	
	@Valid
	private AppointmentModel appointment;
	
	@Valid
	private FeedbackModel feedback;

}
