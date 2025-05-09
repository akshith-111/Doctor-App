package com.doctor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
@Service
public interface IDoctorService {

	public Doctor saveDoctor(Doctor doctor);
	public Doctor updateDoctor(Doctor doctor);
	public List<Doctor> getDoctorList(Doctor doctor);
	public AvailabilityDates addAvailability(AvailabilityDates availabilityDates);
	public AvailabilityDates updateAvailability(AvailabilityDates availabilityDates);
	public Doctor getDoctor(Doctor doctor);
	public Doctor removeDoctor(Doctor doctor);
	public Doctor getDoctorListBySpeciality(String Speciality);



}
