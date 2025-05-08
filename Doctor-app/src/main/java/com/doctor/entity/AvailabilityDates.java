package com.doctor.entity;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
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
	private int availabilityId;
	@OneToOne
	@JsonBackReference
	private Doctor doctor;
	private Date fromDate;
	private Date endDate;
	
}
