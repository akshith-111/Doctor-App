package com.doctor.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

@Service
public interface IPatientService {

	public Patient savePatient(Patient patient);
	public Patient updatePatient(Patient patient);
	public Patient removePatient(Patient patient);
	public Patient getPatient(Patient patient);
	public List<Patient> getAllPatients();
	public Patient getPatientListByDoctor(Doctor doctor);
	public Patient getPatientListByDate(LocalDate appDate);

}
