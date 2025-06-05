package com.doctor.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
	
	private final IPatientService patientService;

	@PostMapping("/doctor/availabilitydates")
	public ResponseEntity<AvailabilityDatesDTO> addAvalilabilityDates(
			@Valid	@RequestBody AvailabilityDatesDTO availabilityDatesDTO) {

		return ResponseEntity.ok(doctorService.addAvailability(availabilityDatesDTO));
	}

	@PutMapping("doctor/updatedates")
	public ResponseEntity<AvailabilityDatesDTO> updateAvaolabilityDates(
			@Valid @RequestBody AvailabilityDatesDTO availabilityDatesDTO) {

		return ResponseEntity.ok(doctorService.updateAvailability(availabilityDatesDTO));
	}

	@GetMapping("doctor/appointmentsbydoctor")
	public ResponseEntity<List<AppointmentDTO>> appointmentsByDoctor(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(appointmentService.getAppointments(doctorModel));
	}

	@GetMapping("doctor/patientsbydoctor")
	public ResponseEntity<List<PatientDTO>> patientsByDoctor(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(patientService.getPatientListByDoctor(doctorModel));
	}
	
	@GetMapping("doctor/feedbacksbydoctor")
	public ResponseEntity<List<FeedbackDTO>> feedbacksByDoctor(@Valid @RequestBody DoctorModel doctorModel) {

		return ResponseEntity.ok(feedbackService.getAllFeedbacks(doctorModel));
	}
	
	@PatchMapping("doctor/updatestatus")
	public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody Map<String, Object> updates) {

		Optional<AppointmentDTO> opt= appointmentService.updateAppointment(updates);
		
		return opt.map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
	}
	

	//Admin operations
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
