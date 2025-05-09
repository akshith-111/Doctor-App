package com.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.AvailabilityDates;
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

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getDoctorList(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AvailabilityDates addAvailability(AvailabilityDates availabilityDates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AvailabilityDates updateAvailability(AvailabilityDates availabilityDates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor getDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor removeDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor getDoctorListBySpeciality(String Speciality) {
		// TODO Auto-generated method stub
		return null;
	}

}
