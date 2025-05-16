package com.doctor.controller;

import java.util.List;
import java.util.Map;

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
import com.doctor.dto.PatientDTO;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.service.IAdminService;
import com.doctor.service.IAppointmentService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

   
	
	private final IAppointmentService appointmentService;

	
	private final IPatientService patientService;


	private final IDoctorService doctorService;
	
	
	private final IAdminService adminService;



	@PatchMapping("/updatestatus")
	public ResponseEntity<String> updateAppointment(@RequestBody Map<String, Object> updates) {

		String status= appointmentService.updateAppointment(updates).getBody().getStatus();
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}

	//PATIENT OPERATIONS
	
	@DeleteMapping("/removepatient")
	public ResponseEntity<PatientDTO> deletePatient(@RequestBody Patient patient) {

		return ResponseEntity.ok(patientService.removePatient(patient));
	}

	@GetMapping("/viewpatient")
	public ResponseEntity<PatientDTO> getPatient(@RequestBody Patient patient) {

		return ResponseEntity.ok(patientService.getPatient(patient));
	}

	@GetMapping("/viewallpatients")
	public ResponseEntity<List<PatientDTO>> getPatientList() {

		return ResponseEntity.ok(patientService.getAllPatients());
	}

	@PutMapping("/modifypatient")
	public ResponseEntity<PatientDTO> modifyPatient(@RequestBody Patient patient) {

		return ResponseEntity.ok(patientService.updatePatient(patient));
	}

	@PostMapping("/addpatient")
	public ResponseEntity<PatientDTO> addPatient(@RequestBody Patient patient) {

		return ResponseEntity.ok(patientService.savePatient(patient));
	}

	@PatchMapping("/modifypatient")
	public ResponseEntity<PatientDTO> modifyPatient(@RequestBody Map<String, Object> updates) {

		return ResponseEntity.ok(patientService.patchUpdatePatient(updates));
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
	public ResponseEntity<List<DoctorDTO>> getDoctors() {
		return ResponseEntity.ok(doctorService.getDoctorList());
	}
	
	@DeleteMapping("/removedoctor")
	public ResponseEntity<DoctorDTO> deleteDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(doctorService.removeDoctor(doctor));
	}
	
	@PutMapping("/modifydoctor")
	public ResponseEntity<DoctorDTO> modifyPatient(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(doctorService.updateDoctor(doctor));
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
	
	
	//status
	@GetMapping("status/{id}")
	public ResponseEntity<String> getAppointmentStatus(@PathVariable int id) {
		String status=appointmentService.getAppointment(id).getBody().getStatus();
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	

}
