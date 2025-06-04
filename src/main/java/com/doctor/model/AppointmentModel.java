package com.doctor.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModel {

	private int appointmentId;
	private PatientModel patient;	
	private DoctorModel doctor;
	private LocalDate appointmentDate;
	private String status;
	private String remark;
}
