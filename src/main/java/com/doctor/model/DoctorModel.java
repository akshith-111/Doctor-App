package com.doctor.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.doctor.dto.AvailabilityDatesDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorModel {

	private int doctorId;
	
	@Length(max = 30,min = 4,message = "name Should be less than 30 Characters")
	private String doctorName;
	
	@Length(max = 30,min = 4,message = "speciality Should be less than 30 Characters")
	private String speciality;
	
	@Length(max = 30,min = 4,message = "hospital Should be less than 30 Characters")
	private String hospitalName;
	
	@Length(max = 10,min = 10,message = "Contact Should be 10 Digits")
	private String mobileNo;
	
	@Email
	private String email;
	
	@Length(max = 50,min = 4,message = "password Should be less than 50 Characters")
	private String password;
	private String chargedPerVisit;
	
	@Valid
	private List<AppointmentModel> appointments;
	
	@Valid
	private FeedbackModel feedback;
	
	@Valid
	private AvailabilityDatesDTO availabilityDates;
}
