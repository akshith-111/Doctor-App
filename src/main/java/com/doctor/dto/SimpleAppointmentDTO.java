package com.doctor.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAppointmentDTO {

	private MiniPatientDTO patient;
	private MiniDoctorDTO doctor;
	private LocalDate appointmentDate;
	private String status;
	private String remark;
}
