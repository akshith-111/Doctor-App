package com.doctor.dto;

import java.util.Date;

import com.doctor.entity.Doctor;

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
public class AvailabilityDatesDTO {

	
//	private int availabilityId;
	private Doctor doctor;
	private Date fromDate;
	private Date endDate;
}
