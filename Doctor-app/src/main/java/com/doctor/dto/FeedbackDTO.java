package com.doctor.dto;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
//	private int feedbackId;
	private String rating;
	
	private Patient patient;
	
	private Doctor doctor;
	private String feedbackComment;
}
