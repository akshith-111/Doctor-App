package com.doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Doctor;
import com.doctor.entity.User;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.UserRepo;

@Service
public class DoctorServiceImpl implements IDoctorService{

	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private User user;
	
	@Override
	public Doctor saveDoctor(Doctor doctor) {
		System.out.println(user);
		doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		user.setPassword(doctor.getPassword());
		user.setRole("DOCTOR");
		user.setUsername(doctor.getEmail());
		userRepo.save(user);
		return doctorRepo.save(doctor);
	}

}
