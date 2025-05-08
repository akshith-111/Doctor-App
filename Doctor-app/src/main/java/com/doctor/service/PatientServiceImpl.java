package com.doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.repo.PatientRepo;
import com.doctor.repo.UserRepo;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private User user;//getting user object
	
	@Autowired
	private UserRepo userRepo;
	
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

}
