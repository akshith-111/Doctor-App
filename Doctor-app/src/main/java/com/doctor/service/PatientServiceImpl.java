package com.doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.doctor.controller.AdminController;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.repo.AdminRepo;
import com.doctor.repo.FeedbackRepo;
import com.doctor.repo.PatientRepo;
import com.doctor.repo.UserRepo;

@Service
public class PatientServiceImpl implements IPatientService {



	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private PatientRepo doctorRepo;
	
	@Autowired
	private User user;//getting user object
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private FeedbackRepo feedbackRepo;
	
	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


     
    
	
	@Override
	public Patient savePatient(Patient patient) {
		patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword())); 
		user.setPassword(patient.getPassword());
		user.setRole("PATIENT");
		user.setUsername(patient.getEmail());
		userRepo.save(user);
		return patientRepo.save(patient);
	}

	@Override
	public Patient updatePatient(Patient patient) {
		
		return patientRepo.save(patient);
	}

	@Override
	public Patient removePatient(Patient patient) {
		try {
			patient=patientRepo.findById(patient.getPatientId())
					.orElseThrow(()->new Exception("NO PATIENT RECORDS"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		if(patient.getAppointment()!=null) {
			patient.getAppointment().setPatient(null);
			patient.getAppointment().setDoctor(null);

		}
		
		if(patient.getFeedback()!=null) {
			patient.getFeedback().setPatient(null);
			patient.getFeedback().setDoctor(null);
		}
		
//		userRepo.delete((User)userRepo.findByUsername(patient.getEmail()));
		apponinmentRadminRepo
		patientRepo.delete(patient);

		return patient; 
		
	}

	@Override
	public Patient getPatient(Patient patient) {
		Optional<Patient> opt=patientRepo.findById(patient.getPatientId());
		opt.orElseThrow();		
		return opt.get();
	}

	@Override
	public List<Patient> getAllPatients() {
		
		return patientRepo.findAll();
	}

	@Override
	public Patient getPatientListByDoctor(Doctor doctor) {
		return null;
	}

	@Override
	public Patient getPatientListByDate(LocalDate appDate) {
		return null;
	}

}
