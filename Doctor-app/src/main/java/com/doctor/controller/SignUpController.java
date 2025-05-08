package com.doctor.controller;

import java.net.http.HttpResponse.ResponseInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Admin;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.service.IAdminService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

@RestController
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private IDoctorService doctorService;
	
	@PostMapping("/admin")
	public Admin adminSignUp(@RequestBody Admin admin) {
		
		return adminService.saveAdmin(admin);
	}
	
	@PostMapping("/patient")
	public Patient patientSignUp(@RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}
	
	@PostMapping("/doctor")
	public Doctor doctorSignUp(@RequestBody Doctor doctor) {
		return doctorService.saveDoctor(doctor);
	}
}
