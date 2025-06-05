package com.doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.dto.AppointmentDTO;
import com.doctor.model.AppointmentModel;
import com.doctor.model.DoctorModel;
import com.doctor.model.PatientModel;

@Service
public interface IAppointmentService {


	//Appointment saveAppointment(String remarks, String date, int doctorId);

	Optional<AppointmentDTO> saveAppointment(AppointmentModel appointmentModel);
	
	List<AppointmentDTO> getAllAppointments();
	
	AppointmentDTO getAppointment(int AppointmentId);
	
	AppointmentDTO deleteAppointment(int AppointmentId);
	
	Optional<AppointmentDTO> updateAppointment(Map<String, Object> updates);
	
	List<AppointmentDTO> getAppointments(DoctorModel doctorModel);
	
	List<AppointmentDTO> getAppointments(LocalDate date);

	AppointmentDTO getAppointment(PatientModel patientModel);


}
