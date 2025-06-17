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
import com.doctor.model.AdminModel;
import com.doctor.model.DoctorModel;
import com.doctor.model.PatientModel;
import com.doctor.service.IAdminService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

import jakarta.validation.Valid;

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
	public ResponseEntity<AdminDTO> adminSignUp(@Valid @RequestBody AdminModel adminModel) {
		
		return ResponseEntity.ok(adminService.saveAdmin(adminModel));
	}
	
	@PostMapping("/patient")
	public ResponseEntity<PatientDTO> patientSignUp(@Valid @RequestBody PatientModel patientModel) {
		return ResponseEntity.ok(patientService.savePatient(patientModel));
	}
	
	@PostMapping("/doctor")
	public ResponseEntity<DoctorDTO> doctorSignUp(@Valid @RequestBody DoctorModel doctorModel) {
		return ResponseEntity.ok(doctorService.saveDoctor(doctorModel));
	}
}
