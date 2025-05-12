package com.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Appointment;
import com.doctor.entity.Patient;
import com.doctor.service.IAppointmentService;
import com.doctor.service.IPatientService;


@RestController
@RequestMapping("/admin")
public class AdminController {


	@Autowired
	IAppointmentService appointmentService;
	
	@Autowired
	IPatientService patientService;

	
	@PatchMapping("/updatestatus")
	public String acceptAppointment(@RequestBody Appointment appointment) {
		
		return appointmentService.updateAppointment(appointment).getStatus();
	}
	
	@DeleteMapping("/deletepatient")
	public Patient deletePatient(@RequestBody Patient patient) {
		
		return patientService.removePatient(patient);
	}
	
	@GetMapping("/getpatient")
	public Patient getPatient(@RequestBody Patient patient) {
		
		return patientService.getPatient(patient);
	}
	
}
