package com.doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.doctor.dto.PatientDTO;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

@Service
public interface IPatientService {

	public PatientDTO savePatient(Patient patient);
	public PatientDTO updatePatient(Patient patient);
	public PatientDTO removePatient(Patient patient);
	public PatientDTO getPatient(Patient patient);
	public List<PatientDTO> getAllPatients();
	public List<PatientDTO> getPatientListByDoctor(Doctor doctor);
	public Patient getPatientListByDate(LocalDate appDate);
	public PatientDTO patchUpdatePatient(Map<String, Object> updates);

}
