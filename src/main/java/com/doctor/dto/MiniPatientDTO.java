package com.doctor.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiniPatientDTO {

	private String patientName;
	private String mobileNo;
	private String email;
	private String bloodGroup;
	private String age;
	private String address;
	
	//private FeedbackDTO feedback;
}
