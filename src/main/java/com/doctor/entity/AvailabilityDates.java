package com.doctor.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AvailabilityDates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int availabilityId;
	@OneToOne
	private Doctor doctor;
	private LocalDate fromDate;
	private LocalDate endDate;
	
}
