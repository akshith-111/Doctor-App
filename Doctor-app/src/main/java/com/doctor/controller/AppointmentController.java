package com.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.repo.DoctorRepo;
import com.doctor.service.IAppointmentService;


@RestController
public class AppointmentController {

		
	@Autowired
	private IAppointmentService appointmentService;
	
	@Autowired
	private DoctorRepo doctorRepo;

    
//	@PostMapping("/appointment/{doctorId}")
//	public Appointment appointment(@RequestParam String remarks ,@RequestParam String date, @PathVariable int doctorId) {
//		
//		return appointmentService.saveAppointment(remarks,date,doctorId);
//		
//	}
	@PostMapping("/appointment")
	public ResponseEntity<HttpStatus> appointment(@RequestBody Appointment appointment) {
		
		return appointmentService.saveAppointment(appointment);
		
	}
	
	@GetMapping("/appointment")
	public List<Appointment> appointments(){
		return appointmentService.getAllAppointments();
		
	}
	
	@GetMapping("/appointment/{id}")
	public Appointment oneAppointment(@PathVariable int id) {
		return appointmentService.getAppointment(id);
	}
	
	@PutMapping("/appointment")
	public Appointment updateAppointment(@RequestBody Appointment appointment) {
		return appointmentService.updateAppointment(appointment);
	}
	
	@DeleteMapping("/appointment/{id}")
	public Appointment deleteAppointment(@PathVariable int id) {
		return appointmentService.deleteAppointment(id);
	}
	@GetMapping("/doctors")
	public List<Doctor> getDoctors() {
		System.out.println("ENTERED");
		return doctorRepo.findAll();
	}
	
	
	
	

}
