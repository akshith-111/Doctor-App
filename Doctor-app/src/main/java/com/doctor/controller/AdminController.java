package com.doctor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.service.IAppointmentService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

@RestController
@RequestMapping("/admin")
public class AdminController {

   
	@Autowired
	IAppointmentService appointmentService;

	@Autowired
	IPatientService patientService;

	@Autowired
	IDoctorService doctorService;



	@PatchMapping("/updatestatus")
	public String updateAppointment(@RequestBody Map<String, Object> updates) {

		return appointmentService.updateAppointment(updates).getStatus();
	}

	//PATIENT OPERATIONS
	
	@DeleteMapping("/removepatient")
	public ResponseEntity<HttpStatus> deletePatient(@RequestBody Patient patient) {

		return patientService.removePatient(patient);
	}

	@GetMapping("/viewpatient")
	public Patient getPatient(@RequestBody Patient patient) {

		return patientService.getPatient(patient);
	}

	@GetMapping("/viewallpatients")
	public ResponseEntity<List<Patient>> getPatientList() {

		return patientService.getAllPatients();
	}

	@PutMapping("/modifypatient")
	public ResponseEntity<Patient> modifyPatient(@RequestBody Patient patient) {

		return patientService.updatePatient(patient);
	}

	@PostMapping("/addpatient")
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {

		return patientService.savePatient(patient);
	}

	@PatchMapping("/modifypatient")
	public ResponseEntity<Patient> modifyPatient(@RequestBody Map<String, Object> updates) {

		return patientService.patchUpdatePatient(updates);
	}
	
	
	//DOCTOR OPERATIONS

	@PostMapping("/adddoctor")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {

		return doctorService.saveDoctor(doctor);
	}
	
	@GetMapping("/getdoctor")
	public ResponseEntity<Doctor> getDoctor(@RequestBody Doctor doctor) {
		return doctorService.getDoctor(doctor);
	}

	@GetMapping("/getalldoctors")
	public ResponseEntity<List<Doctor>> getDoctors() {
		return doctorService.getDoctorList();
	}
	
	@DeleteMapping("/removedoctor")
	public ResponseEntity<Doctor> deleteDoctor(@RequestBody Doctor doctor) {

		return doctorService.removeDoctor(doctor);
	}
	
	

}
