package com.doctor.dto;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
//	private int feedbackId;
	private String rating;
	
	private MiniPatientDTO patient;
	
	private MiniDoctorDTO doctor;
	private String feedbackComment;
}
