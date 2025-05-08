package com.doctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

	List<Appointment> findByDoctor(Doctor doctor);
	
	
	
}
