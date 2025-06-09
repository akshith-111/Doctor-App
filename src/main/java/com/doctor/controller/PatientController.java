package com.doctor.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.DoctorDTO;
import com.doctor.dto.MiniPatientDTO;
import com.doctor.dto.PatientDTO;
import com.doctor.model.DoctorModel;
import com.doctor.model.PatientModel;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

import jakarta.validation.Valid;
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
	
	@DeleteMapping("admin/removepatient/{id}")
	public ResponseEntity<PatientDTO> deletePatient(@PathVariable int id){

		return ResponseEntity.ok(patientService.removePatient(id));
	}

	@GetMapping("admin/viewallpatients")
	public ResponseEntity<List<PatientDTO>> getPatientList() {

		return ResponseEntity.ok(patientService.getAllPatients());
	}
	
	@GetMapping("doctor/patientsbydoctor")
	public ResponseEntity<List<PatientDTO>> patientsByDoctor(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(patientService.getPatientListByDoctor(doctorModel));
	}

	@PutMapping("admin/modifypatient")
	public ResponseEntity<PatientDTO> modifyPatient(@Valid @RequestBody PatientModel patientModel) {

		return ResponseEntity.ok(patientService.updatePatient(patientModel));
	}

	@PostMapping("admin/addpatient")
	public ResponseEntity<PatientDTO> addPatient(@Valid @RequestBody PatientModel patientModel) {

		return ResponseEntity.ok(patientService.savePatient(patientModel));
	}

	@PatchMapping("admin/modifypatient")
	public ResponseEntity<PatientDTO> modifyPatient(@RequestBody Map<String, Object> updates) {

		return ResponseEntity.ok(patientService.patchUpdatePatient(updates));
	}
	
	
	@GetMapping("/getpatientshistory")
	public ResponseEntity<List<MiniPatientDTO>> getPatientsHistory(@RequestParam(required = false) LocalDate date,@RequestParam(required = false) String doctorName){
			
		Optional<List<MiniPatientDTO>> opt= patientService.getAllPatientsHistory(date,doctorName);
		if(opt.isPresent())
			return ResponseEntity.ok(opt.get());
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(List.of());
		ResponseCookie cookie=ResponseCookie.from(doctorName);
	}
}
