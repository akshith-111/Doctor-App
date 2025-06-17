package com.doctor.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentHistory {


	public TreatmentHistory(Doctor doctor, Patient patient, LocalDate treatementDate) {
		super();
		this.doctor = doctor;
		this.patient = patient;
		this.treatementDate = treatementDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	private LocalDate treatementDate;

	
	
}
