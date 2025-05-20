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
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.AppointmentDTO;
import com.doctor.dto.AvailabilityDatesDTO;
import com.doctor.dto.DoctorDTO;
import com.doctor.dto.FeedbackDTO;
import com.doctor.dto.PatientDTO;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.service.IAppointmentService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IFeedbackService;
import com.doctor.service.IPatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final IFeedbackService feedbackService;
    
	private final IDoctorService doctorService;
	
	private final IAppointmentService appointmentService;
	
	private final IPatientService patientService;

	@PostMapping("/doctor/availabilitydates")
	public ResponseEntity<AvailabilityDatesDTO> addAvalilabilityDates(
			@RequestBody AvailabilityDates availabilityDates) {

		return ResponseEntity.ok(doctorService.addAvailability(availabilityDates));
	}

	@PutMapping("doctor/updatedates")
	public ResponseEntity<AvailabilityDatesDTO> updateAvaolabilityDates(
			@RequestBody AvailabilityDates availabilityDates) {

		return ResponseEntity.ok(doctorService.updateAvailability(availabilityDates));
	}

	@GetMapping("doctor/appointmentsbydoctor")
	public ResponseEntity<List<AppointmentDTO>> appointmentsByDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(appointmentService.getAppointments(doctor));
	}

	@GetMapping("doctor/patientsbydoctor")
	public ResponseEntity<List<PatientDTO>> patientsByDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(patientService.getPatientListByDoctor(doctor));
	}
	
	@GetMapping("doctor/feedbacksbydoctor")
	public ResponseEntity<List<FeedbackDTO>> feedbacksByDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(feedbackService.getAllFeedbacks(doctor));
	}
	
	@PatchMapping("doctor/updatestatus")
	public ResponseEntity<String> updateAppointment(@RequestBody Map<String, Object> updates) {

		String status= appointmentService.updateAppointment(updates).getBody().getStatus();
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	

	//Admin operations
	@PostMapping("admin/adddoctor")
	public ResponseEntity<DoctorDTO> addDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(doctorService.saveDoctor(doctor));
	}
	
	@GetMapping("getdoctor/{id}")
	public ResponseEntity<DoctorDTO> getDoctor(@PathVariable int id) {
		return doctorService.getDoctor(id);
	}

	@GetMapping("admin/getalldoctors")
	public ResponseEntity<List<DoctorDTO>> getDoctors() {
		return ResponseEntity.ok(doctorService.getDoctorList());
	}
	
	@DeleteMapping("admin/removedoctor")
	public ResponseEntity<DoctorDTO> deleteDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(doctorService.removeDoctor(doctor));
	}
	
	@PutMapping("admin/modifydoctor")
	public ResponseEntity<DoctorDTO> modifyPatient(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(doctorService.updateDoctor(doctor));
	}
	
	
	
	
	
	
}
