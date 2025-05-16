package com.doctor.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.dto.AvailabilityDatesDTO;
import com.doctor.dto.DoctorDTO;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
@Service
public interface IDoctorService {

	public DoctorDTO saveDoctor(Doctor doctor);
	public DoctorDTO updateDoctor(Doctor doctor);
	public List<DoctorDTO> getDoctorList();
	public AvailabilityDatesDTO addAvailability(AvailabilityDates availabilityDates);
	public AvailabilityDatesDTO updateAvailability(AvailabilityDates availabilityDates);
//	public ResponseEntity<DoctorDTO> getDoctor(Doctor doctor);
	public DoctorDTO removeDoctor(Doctor doctor);
	public Doctor getDoctorListBySpeciality(String Speciality);
	public ResponseEntity<DoctorDTO> getDoctor(int id);



}
