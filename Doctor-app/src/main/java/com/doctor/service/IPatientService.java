package com.doctor.service;

import org.springframework.stereotype.Service;

import com.doctor.entity.Patient;

@Service
public interface IPatientService {

	public Patient savePatient(Patient patient);
}
