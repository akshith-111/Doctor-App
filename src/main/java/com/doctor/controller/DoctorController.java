package com.doctor.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Optional;

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
import com.doctor.model.ApiResponse;
import com.doctor.model.AuthModel;
import com.doctor.model.DoctorModel;
import com.doctor.service.IAppointmentService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IFeedbackService;
import com.doctor.service.IPatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DoctorController {

	
	private final IFeedbackService feedbackService;

	private final IDoctorService doctorService;

	private final IAppointmentService appointmentService;

	@PostMapping("/doctor/availabilitydates")
	public ResponseEntity<AvailabilityDatesDTO> addAvalilabilityDates(
			@Valid @RequestBody AvailabilityDatesDTO availabilityDatesDTO) {

		return ResponseEntity.ok(doctorService.addAvailability(availabilityDatesDTO));
	}

	@PutMapping("doctor/updatedates")
	public ResponseEntity<?> updateAvailabilityDates(
			@Valid @RequestBody AvailabilityDatesDTO availabilityDatesDTO) {
		Optional<AvailabilityDatesDTO> opt= doctorService.updateAvailability(availabilityDatesDTO);
		if(opt.isPresent())
			return ResponseEntity.ok(opt.get());
		else
		return ResponseEntity.status(HttpStatus.ACCEPTED).body( new ApiResponse("SUCCESS","Cannot Update Dates"));
	}

	@GetMapping("doctor/appointmentsbydoctor")
	public ResponseEntity<List<AppointmentDTO>> appointmentsByDoctor(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(appointmentService.getAppointments(doctorModel));
	}

	@GetMapping("doctor/feedbacksbydoctor")
	public ResponseEntity<List<FeedbackDTO>> feedbacksByDoctor(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(feedbackService.getAllFeedbacks(doctorModel));
	}

	@PatchMapping("doctor/updatestatus")
	public ResponseEntity<?> updateAppointment(@RequestBody Map<String, Object> updates) {

		Optional<AppointmentDTO> opt= appointmentService.updateAppointment(updates);
		
		if(opt.isPresent())
			return ResponseEntity.ok(opt.get());
		else
		return ResponseEntity.ok(new ApiResponse("SUCCESS","NOT ALLOWED TO CHANGE STATUS"));
	}

	// Admin's operations
	@PostMapping("admin/adddoctor")
	public ResponseEntity<DoctorDTO> addDoctor(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(doctorService.saveDoctor(doctorModel));
	}

	@GetMapping("getdoctor/{id}")
	public ResponseEntity<DoctorDTO> getDoctor(@PathVariable int id) {
		return doctorService.getDoctor(id);
	}

	@GetMapping("admin/getalldoctors")
	public ResponseEntity<List<DoctorDTO>> getDoctors() {
		return ResponseEntity.ok(doctorService.getDoctorList());
	}

	@DeleteMapping("admin/removedoctor/{id}")
	public ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable int id) {

		return ResponseEntity.ok(doctorService.removeDoctor(id));
	}

	@PutMapping("admin/modifydoctor")
	public ResponseEntity<DoctorDTO> modifyPatient(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(doctorService.updateDoctor(doctorModel));
	}

}
