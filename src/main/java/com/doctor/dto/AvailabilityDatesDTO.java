package com.doctor.dto;

import java.util.Date;

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
	private Date fromDate;
	private Date endDate;
}
