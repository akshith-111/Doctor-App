package com.doctor.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.dto.MiniPatientDTO;
import com.doctor.dto.PatientDTO;
import com.doctor.entity.Patient;
import com.doctor.model.DoctorModel;
import com.doctor.model.PatientModel;

@Service
public interface IPatientService {

	public PatientDTO savePatient(PatientModel patientModel);
	
	public PatientDTO updatePatient(PatientModel patientModel);
	
	public PatientDTO removePatient(int id);
	
	public PatientDTO getPatient(int id);
	
	public List<PatientDTO> getAllPatients();
	
	public List<PatientDTO> getPatientListByDoctor(DoctorModel doctorModel);
	
	public Patient getPatientListByDate(LocalDate appDate);
	
	public PatientDTO patchUpdatePatient(Map<String, Object> updates);

	public Optional<List<MiniPatientDTO>> getAllPatientsHistory(LocalDate date, String doctorName);

}
