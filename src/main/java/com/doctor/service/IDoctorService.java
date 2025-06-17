package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.dto.AvailabilityDatesDTO;
import com.doctor.dto.DoctorDTO;
import com.doctor.model.DoctorModel;
@Service
public interface IDoctorService {

	public DoctorDTO saveDoctor(DoctorModel doctorModel);
	public DoctorDTO updateDoctor(DoctorModel doctorModel);
	public List<DoctorDTO> getDoctorList();
	public AvailabilityDatesDTO addAvailability(AvailabilityDatesDTO availabilityDatesDTO);
	public Optional<AvailabilityDatesDTO> updateAvailability(AvailabilityDatesDTO availabilityDatesDTO);
//	public ResponseEntity<DoctorDTO> getDoctor(Doctor doctor);
	public DoctorDTO removeDoctor(int id);
	public List<DoctorDTO> getDoctorListBySpeciality(String speciality);
	public ResponseEntity<DoctorDTO> getDoctor(int id);



}
