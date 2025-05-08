package com.doctor.service;

import org.springframework.stereotype.Service;

import com.doctor.entity.Doctor;
@Service
public interface IDoctorService {

	public Doctor saveDoctor(Doctor doctor);
}
