package com.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.AvailabilityDates;
import com.doctor.repo.AvailabilityDatesRepo;
import com.doctor.repo.DoctorRepo;
import com.doctor.service.IDoctorService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;
	
	
	@PostMapping("/availabilitydates")
	public ResponseEntity<AvailabilityDates> addAvalilability(@RequestBody AvailabilityDates availabilityDates ) {
		
		
		return doctorService.addAvailability(availabilityDates);
	}
	
}
