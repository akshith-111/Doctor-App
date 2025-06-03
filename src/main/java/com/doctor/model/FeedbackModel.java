package com.doctor.model;

import org.springframework.stereotype.Component;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackModel {

private String rating;
	
	private Patient patient;
	private Doctor doctor;
	private String feedbackComment;
}
