package com.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.AdminDTO;
import com.doctor.dto.DoctorDTO;
import com.doctor.dto.PatientDTO;
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
	public ResponseEntity<AdminDTO> adminSignUp(@RequestBody Admin admin) {
		
		return ResponseEntity.ok(adminService.saveAdmin(admin));
	}
	
	@PostMapping("/patient")
	public ResponseEntity<PatientDTO> patientSignUp(@RequestBody Patient patient) {
		return ResponseEntity.ok(patientService.savePatient(patient));
	}
	
	@PostMapping("/doctor")
	public ResponseEntity<DoctorDTO> doctorSignUp(@RequestBody Doctor doctor) {
		return ResponseEntity.ok(doctorService.saveDoctor(doctor));
	}
}
