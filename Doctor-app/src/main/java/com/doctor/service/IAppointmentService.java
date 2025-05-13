package com.doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;

@Service
public interface IAppointmentService {


	//Appointment saveAppointment(String remarks, String date, int doctorId);

	ResponseEntity<HttpStatus> saveAppointment(Appointment appointment);
	
	List<Appointment> getAllAppointments();
	
	Appointment getAppointment(int AppointmentId);
	
	Appointment deleteAppointment(int AppointmentId);
	
	Appointment updateAppointment(Map<String, Object> updates);
	
	List<Appointment> getAppointments(Doctor doctor);
	
	List<Appointment> getAppointments(LocalDate date);


}
