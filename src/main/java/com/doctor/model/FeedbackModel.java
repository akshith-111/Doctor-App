package com.doctor.model;

import org.springframework.stereotype.Component;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackModel {

	
	private String rating;
	
	@Valid
	private PatientModel patient;
	
	@Valid
	private DoctorModel doctor;
	
	private String feedbackComment;
}
