package com.doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.repo.AppointmentRepo;
import com.doctor.repo.FeedbackRepo;
import com.doctor.repo.PatientRepo;
import com.doctor.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class PatientServiceImpl implements IPatientService {

	
	@Autowired
	private AppointmentRepo appointmentRepo;

	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private PatientRepo doctorRepo;

	@Autowired
	private User user;// getting user object

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private FeedbackRepo feedbackRepo;

	@Autowired
	private IUserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ResponseEntity<Patient> savePatient(Patient patient) {
		patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
		user.setPassword(patient.getPassword());
		user.setRole("PATIENT");
		user.setUsername(patient.getEmail());
		userRepo.save(user);
		return ResponseEntity.ok(patientRepo.save(patient));
	}

	@Override
	public ResponseEntity<Patient> updatePatient(Patient patient) {

		return ResponseEntity.ok(patientRepo.save(patient));
	}

	@Override
	@Transactional
	public ResponseEntity<HttpStatus> removePatient(Patient patient) {

		patient = patientRepo.findById(patient.getPatientId()).get();
		userRepo.delete((User) userRepo.findByUsername(patient.getEmail()));

		Appointment appointment = patient.getAppointment();
		Feedback feedback = patient.getFeedback();

		if (appointment != null) {
			appointment.setPatient(null);
			appointment.getDoctor().setAppointments(null);
			appointment.setDoctor(null);

		}

		if (feedback != null) {

			feedback.setPatient(null);
			feedback.getDoctor().setFeedback(null);
			feedback.setDoctor(null);
		}
		

		patientRepo.deleteById(patient.getPatientId());
		
		return ResponseEntity.ok(HttpStatus.ACCEPTED);

	}

	@Override
	public Patient getPatient(Patient patient) {
		Optional<Patient> opt = patientRepo.findById(patient.getPatientId());
		opt.orElseThrow();
		return opt.get();
	}

	@Override
	public ResponseEntity<List<Patient>> getAllPatients() {

		return ResponseEntity.ok(patientRepo.findAll());
	}

	@Override
	public Patient getPatientListByDoctor(Doctor doctor) {
		return null;
	}

	@Override
	public Patient getPatientListByDate(LocalDate appDate) {
		return null;
	}

	@Override
	public ResponseEntity<Patient> patchUpdatePatient(Map<String, Object> updates) {

		Patient patient = patientRepo.findById(Integer.valueOf(String.valueOf(updates.get("patientId")))).get();

		if (updates.containsKey("patientName"))
			patient.setPatientName(String.valueOf(updates.get("patientName")));
		
		if (updates.containsKey("mobileNo"))
			patient.setMobileNo(String.valueOf(updates.get("mobileNo")));
		
		if (updates.containsKey("email"))
			patient.setEmail(String.valueOf((updates.get("email"))));
		
		if (updates.containsKey("bloodGroup"))
			patient.setBloodGroup(String.valueOf(updates.get("bloodGroup")));
		
		if (updates.containsKey("age"))
			patient.setAge(String.valueOf(updates.get("age")));
		
		if (updates.containsKey("address"))
			patient.setAddress(String.valueOf((updates.get("address"))));
		
		if (updates.containsKey("password"))
			patient.setPassword(bCryptPasswordEncoder.encode(String.valueOf(updates.get("password"))));
		
		if (updates.containsKey("appointment"))
			patient.setAppointment((Appointment) (updates.get("appointment")));
		
		if (updates.containsKey("feedback"))
			patient.setFeedback((Feedback) (updates.get("feedback")));
		
			patientRepo.save(patient);
		return ResponseEntity.ok(patient);
	}

}
