package com.doctor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.DoctorDTO;
import com.doctor.dto.PatientDTO;
import com.doctor.entity.Patient;
import com.doctor.model.PatientModel;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController {

	
	private final IPatientService patientService;
	
	private final IDoctorService doctorService;
	
	@GetMapping("patient/getalldoctors")
	public ResponseEntity<List<DoctorDTO>> getDoctors() {
		return ResponseEntity.ok(doctorService.getDoctorList());
	}
	
	@GetMapping("admin/viewpatient/{id}")
	public ResponseEntity<PatientDTO> getPatientByAdmin(@PathVariable int id) {

		return ResponseEntity.ok(patientService.getPatient(id));
	}
	@GetMapping("patient/viewpatient/{id}")
	public ResponseEntity<PatientDTO> getPatient(@PathVariable int id) {

		return ResponseEntity.ok(patientService.getPatient(id));
	}
	
	@DeleteMapping("admin/removepatient")
	public ResponseEntity<PatientDTO> deletePatient(@RequestBody PatientModel patientModel) {

		return ResponseEntity.ok(patientService.removePatient(patientModel));
	}

	@GetMapping("admin/viewallpatients")
	public ResponseEntity<List<PatientDTO>> getPatientList() {

		return ResponseEntity.ok(patientService.getAllPatients());
	}

	@PutMapping("admin/modifypatient")
	public ResponseEntity<PatientDTO> modifyPatient(@RequestBody PatientModel patientModel) {

		return ResponseEntity.ok(patientService.updatePatient(patientModel));
	}

	@PostMapping("admin/addpatient")
	public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientModel patientModel) {

		return ResponseEntity.ok(patientService.savePatient(patientModel));
	}

	@PatchMapping("admin/modifypatient")
	public ResponseEntity<PatientDTO> modifyPatient(@RequestBody Map<String, Object> updates) {

		return ResponseEntity.ok(patientService.patchUpdatePatient(updates));
	}
}
