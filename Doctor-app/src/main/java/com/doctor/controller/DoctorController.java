package com.doctor.controller;
import com.doctor.service.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.AvailabilityDates;
import com.doctor.service.IDoctorService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class DoctorController {


	@Autowired
	private IDoctorService doctorService;

	@PostMapping("/availabilitydates")
	public ResponseEntity<AvailabilityDates> addAvalilability(@RequestBody AvailabilityDates availabilityDates ) {
		
		
		return doctorService.addAvailability(availabilityDates);
	}
	
	@PutMapping("updatedates")
	public ResponseEntity<AvailabilityDates> putMethodName(@RequestBody AvailabilityDates availabilityDates) {
		
		return doctorService.updateAvailability(availabilityDates);
	}
}
