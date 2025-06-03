package com.doctor.dto;

import java.time.LocalDate;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
	
//	private int appointmentId;

	private Patient patient;
	private Doctor doctor;
	private LocalDate appointmentDate;
	private String status;
	private String remark;
}
