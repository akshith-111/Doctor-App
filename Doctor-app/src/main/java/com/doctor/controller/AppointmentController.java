package com.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.AppointmentDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.Patient;
import com.doctor.service.IAppointmentService;



@RestController
@RequestMapping("/patient")
public class AppointmentController {

		
	@Autowired
	private IAppointmentService appointmentService;
	

	@PostMapping("/appointment")
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<AppointmentDTO> appointment(@RequestBody Appointment appointment) {
		
		return appointmentService.saveAppointment(appointment)
				.map(ResponseEntity::ok)
					.orElse(ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build());
		
	}
	
	@GetMapping("/appointment")
	public ResponseEntity<List<AppointmentDTO>> appointments(){
		
		return appointmentService.getAllAppointments();
		
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<AppointmentDTO> oneAppointment(@PathVariable int id) {
		return appointmentService.getAppointment(id);
	}
	
	
	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<AppointmentDTO> deleteAppointment(@PathVariable int id) {
		return appointmentService.deleteAppointment(id);
	}
	
	
	@GetMapping("/appointmentbypatient")
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<AppointmentDTO> oneAppointment(@RequestBody Patient patient){
		return ResponseEntity.ok(appointmentService.getAppointment(patient));
	}
	
	
	
	
	
	

}
