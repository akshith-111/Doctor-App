package com.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.AppointmentDTO;
import com.doctor.dto.AvailabilityDatesDTO;
import com.doctor.dto.FeedbackDTO;
import com.doctor.dto.PatientDTO;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.service.FeedbackService;
import com.doctor.service.IAppointmentService;
import com.doctor.service.IDoctorService;
import com.doctor.service.IPatientService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
    private FeedbackService feedbackService;

	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private IAppointmentService appointmentService;
	
	@Autowired
	private IPatientService patientService;

	@PostMapping("/availabilitydates")
	public ResponseEntity<AvailabilityDatesDTO> addAvalilabilityDates(
			@RequestBody AvailabilityDates availabilityDates) {

		return ResponseEntity.ok(doctorService.addAvailability(availabilityDates));
	}

	@PutMapping("updatedates")
	public ResponseEntity<AvailabilityDatesDTO> updateAvaolabilityDates(
			@RequestBody AvailabilityDates availabilityDates) {

		return ResponseEntity.ok(doctorService.updateAvailability(availabilityDates));
	}

	@GetMapping("appointmentsbydoctor")
	public ResponseEntity<List<AppointmentDTO>> appointmentsByDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(appointmentService.getAppointments(doctor));
	}

	@GetMapping("patientsbydoctor")
	public ResponseEntity<List<PatientDTO>> patientsByDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(patientService.getPatientListByDoctor(doctor));
	}
	
	@GetMapping("feedbacksbydoctor")
	public ResponseEntity<List<FeedbackDTO>> feedbacksByDoctor(@RequestBody Doctor doctor) {

		return ResponseEntity.ok(feedbackService.getAllFeedbacks(doctor));
	}
	

	
}
