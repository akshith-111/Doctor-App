package com.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

	private String rating;
	
	private MiniPatientDTO patient;
	
	private MiniDoctorDTO doctor;
	private String feedbackComment;
}
