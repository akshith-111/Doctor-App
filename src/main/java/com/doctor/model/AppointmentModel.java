package com.doctor.model;

import java.time.LocalDate;


import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModel {

	private int appointmentId;
	@Valid
	private PatientModel patient;
	@Valid
	private DoctorModel doctor;
	private LocalDate appointmentDate;
	
	@Length(max = 8,min = 8,message = "status Should be less than 50 Characters")
	private String status;
	private String remark;
}
