package com.doctor.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiniDoctorDTO {

	private String doctorName;
	private String speciality;
	private String hospitalName;
	private String mobileNo;
	private String email;
	private String chargedPerVisit;
	

//	private List<FeedbackDTO> feedback;
	
//	private AvailabilityDatesDTO availabilityDates;
}
