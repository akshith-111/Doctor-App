package com.doctor.dto;

import java.util.List;

import com.doctor.entity.Appointment;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Feedback;

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
	private String password;
	private String chargedPerVisit;
	
	private List<Appointment> appointments;
	
	private Feedback feedback;
	
	private AvailabilityDates availabilityDates;

}
