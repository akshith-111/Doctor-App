package com.doctor.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.dto.AppointmentDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;

@Service
public interface IAppointmentService {


	//Appointment saveAppointment(String remarks, String date, int doctorId);

	Optional<AppointmentDTO> saveAppointment(Appointment appointment);
	
	ResponseEntity<List<AppointmentDTO>> getAllAppointments();
	
	ResponseEntity<AppointmentDTO> getAppointment(int AppointmentId);
	
	ResponseEntity<AppointmentDTO> deleteAppointment(int AppointmentId);
	
	ResponseEntity<AppointmentDTO> updateAppointment(Map<String, Object> updates);
	
	List<AppointmentDTO> getAppointments(Doctor doctor);
	
	List<AppointmentDTO> getAppointments(LocalDate date);

	AppointmentDTO getAppointment(Patient patient);


}
