package com.doctor.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
@Service
public interface IDoctorService {

	public ResponseEntity<Doctor> saveDoctor(Doctor doctor);
	public Doctor updateDoctor(Doctor doctor);
	public ResponseEntity<List<Doctor>> getDoctorList();
	public ResponseEntity<AvailabilityDates> addAvailability(AvailabilityDates availabilityDates);
	public ResponseEntity<AvailabilityDates> updateAvailability(AvailabilityDates availabilityDates);
	public ResponseEntity<Doctor> getDoctor(Doctor doctor);
	public ResponseEntity<Doctor> removeDoctor(Doctor doctor);
	public Doctor getDoctorListBySpeciality(String Speciality);



}
