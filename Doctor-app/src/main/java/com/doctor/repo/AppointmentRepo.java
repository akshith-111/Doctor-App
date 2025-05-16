package com.doctor.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.dto.AppointmentDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

	public List<Appointment> findByDoctor(Doctor doctor);

	public List<Appointment> findByAppointmentDate(LocalDate date);

	public AppointmentDTO findByPatient(Patient patient);
	
	
	
}
