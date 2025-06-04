package com.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleFeedbackDTO {

	private String rating;

	private int patientId;

	private int doctorId;
	private String feedbackComment;

}
