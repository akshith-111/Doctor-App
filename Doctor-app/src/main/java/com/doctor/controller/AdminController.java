package com.doctor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.AdminDTO;
import com.doctor.dto.DoctorDTO;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.service.IAdminService;
import com.doctor.service.IAppointmentService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

@RestController
@RequestMapping("/admin")
public class AdminController {

   
	@Autowired
	private IAppointmentService appointmentService;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private IAdminService adminService;



	@PatchMapping("/updatestatus")
	public ResponseEntity<String> updateAppointment(@RequestBody Map<String, Object> updates) {

		String status= appointmentService.updateAppointment(updates).getBody().getStatus();
		return new ResponseEntity<String>(status,HttpStatus.OK);
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
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(doctorService.saveDoctor(doctor));
	}
	
	@GetMapping("/getdoctor/{id}")
	public ResponseEntity<DoctorDTO> getDoctor(@PathVariable int id) {
		return doctorService.getDoctor(id);
	}

	@GetMapping("/getalldoctors")
	public ResponseEntity<List<Doctor>> getDoctors() {
		return doctorService.getDoctorList();
	}
	
	@DeleteMapping("/removedoctor")
	public ResponseEntity<Doctor> deleteDoctor(@RequestBody Doctor doctor) {

		return doctorService.removeDoctor(doctor);
	}
	
	//ADMIN OPERATIONS
	
	@GetMapping("/getadmin/{id}")
	public ResponseEntity<AdminDTO> getadmin(@PathVariable int id) {
		return adminService.getAdmin(id);
	}
	@GetMapping("/getadmins")
	public ResponseEntity<List<AdminDTO>> getadmins() {
		return adminService.getAdminList();
	}
	

}
