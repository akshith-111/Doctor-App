package com.doctor.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

//	private int doctorId;
	private String doctorName;
	private String speciality;
	private String hospitalName;
	private String mobileNo;
	private String email;
	private String chargedPerVisit;
	
	private List<SimpleAppointmentDTO> appointments;
	
	private List<SimpleFeedbackDTO> feedback;
	
	private AvailabilityDatesDTO availabilityDates;

}
