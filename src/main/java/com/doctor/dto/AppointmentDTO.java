package com.doctor.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
	
//	private int appointmentId;

	private MiniPatientDTO patient;
	private MiniDoctorDTO doctor;
	private LocalDate appointmentDate;
	private String status;
	private String remark;
}
