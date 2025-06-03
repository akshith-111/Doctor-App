package com.doctor.model;

import org.springframework.stereotype.Component;

import com.doctor.entity.Appointment;
import com.doctor.entity.Feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientModel {

	private String patientName;
	private String mobileNo;
	private String email;
	private String bloodGroup;
	private String age;
	private String address;
	private String password;
	private Appointment appointment;
	private Feedback feedback;

}
