package com.doctor.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.doctor.dto.AvailabilityDatesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorModel {

	private String doctorName;
	private String speciality;
	private String hospitalName;
	private String mobileNo;
	private String email;
	private String password;
	private String chargedPerVisit;
	
	private List<AppointmentModel> appointments;
	
	private FeedbackModel feedback;
	
	private AvailabilityDatesDTO availabilityDates;
}
