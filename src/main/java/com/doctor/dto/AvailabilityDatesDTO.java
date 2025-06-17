package com.doctor.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AvailabilityDatesDTO {

	private int doctorId;
	private int availabilityId;
	private LocalDate fromDate;
	private LocalDate endDate;
}
