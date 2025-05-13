package com.doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

@Service
public interface IPatientService {

	public ResponseEntity<Patient> savePatient(Patient patient);
	public ResponseEntity<Patient> updatePatient(Patient patient);
	public ResponseEntity<HttpStatus> removePatient(Patient patient);
	public Patient getPatient(Patient patient);
	public ResponseEntity<List<Patient>> getAllPatients();
	public Patient getPatientListByDoctor(Doctor doctor);
	public Patient getPatientListByDate(LocalDate appDate);
	public ResponseEntity<Patient> patchUpdatePatient(Map<String, Object> updates);

}
