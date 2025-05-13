package com.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.entity.User;
import com.doctor.repo.AvailabilityDatesRepo;
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

	@Autowired
	AvailabilityDatesRepo availabilityDatesRepo;
	
	@Override
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
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Doctor doctor=doctorRepo.findByEmail(user.getUsername());
		availabilityDates.setDoctor(doctor);
		
		return ResponseEntity.ok(availabilityDatesRepo.save(availabilityDates));
	}

	@Override
	public AvailabilityDates updateAvailability(AvailabilityDates availabilityDates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Doctor> getDoctor(Doctor doctor) {
		
		doctor=doctorRepo.findById(doctor.getDoctorId()).get();		
		return ResponseEntity.ok(doctor);
	}

	@Override
	public ResponseEntity<Doctor> removeDoctor(Doctor doctor) {
		doctor=doctorRepo.findById(doctor.getDoctorId()).get();
		//Appointment doctor.getAppointments();
		return null;
	}

	@Override
	public Doctor getDoctorListBySpeciality(String Speciality) {
		// TODO Auto-generated method stub
		return null;
	}

}
