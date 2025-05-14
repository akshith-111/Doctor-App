package com.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Appointment;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.repo.AppointmentRepo;
import com.doctor.repo.AvailabilityDatesRepo;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.FeedbackRepo;
import com.doctor.repo.PatientRepo;
import com.doctor.repo.UserRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DoctorServiceImpl implements IDoctorService {

	private final PatientRepo patientRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private User user;

	@Autowired
	AvailabilityDatesRepo availabilityDatesRepo;

	@Autowired
	AppointmentRepo appointmentRepo;

	@Autowired
	FeedbackRepo feedbackRepo;

	DoctorServiceImpl(PatientRepo patientRepo) {
		this.patientRepo = patientRepo;
	}

	@Override
	@Transactional
	public ResponseEntity<Doctor> saveDoctor(Doctor doctor) {
		System.out.println(user);
		doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		user.setPassword(doctor.getPassword());
		user.setRole("DOCTOR");
		user.setUsername(doctor.getEmail());
		userRepo.save(user);
		return ResponseEntity.ok(doctorRepo.save(doctor));
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Doctor>> getDoctorList() {

		return ResponseEntity.ok(doctorRepo.findAll());
	}

	@Override
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public ResponseEntity<AvailabilityDates> addAvailability(AvailabilityDates availabilityDates) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Doctor doctor = doctorRepo.findByEmail(user.getUsername());
		availabilityDates.setDoctor(doctor);

		return ResponseEntity.ok(availabilityDatesRepo.save(availabilityDates));
	}

	@Override
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public ResponseEntity<AvailabilityDates> updateAvailability(AvailabilityDates availabilityDates) {
		AvailabilityDates actualAvailabilityDates = availabilityDatesRepo
				.findById(availabilityDates.getAvailabilityId()).get();

		actualAvailabilityDates.setEndDate(availabilityDates.getEndDate());
		actualAvailabilityDates.setFromDate(availabilityDates.getFromDate());
		return ResponseEntity.ok(actualAvailabilityDates);
	}

	@Override
	public ResponseEntity<Doctor> getDoctor(Doctor doctor) {

		doctor = doctorRepo.findById(doctor.getDoctorId()).get();
		return ResponseEntity.ok(doctor);
	}

	@Override
	@Transactional
	public ResponseEntity<Doctor> removeDoctor(Doctor doctor) {
		doctor = doctorRepo.findById(doctor.getDoctorId()).get();

		List<Appointment> appointments = doctor.getAppointments();
		Feedback feedback = doctor.getFeedback();

		if(appointments!=null) {
			appointments.forEach(appo->{
				Patient patient= appo.getPatient();
						patient.setAppointment(null);
						patientRepo.save(patient);
			});
		}

		if (feedback != null) {
			feedback.getDoctor().setFeedback(null);
			feedback.getPatient().setFeedback(null);
			feedback.setDoctor(null);
			feedback.setPatient(null);

		}
		
		doctorRepo.delete(doctor);
		userRepo.delete((User) userRepo.findByUsername(doctor.getEmail()));
		
		entityManager.clear();
		
		return ResponseEntity.ok(doctor);
	}

	@Override
	public Doctor getDoctorListBySpeciality(String Speciality) {
		
		return null;
	}

}
