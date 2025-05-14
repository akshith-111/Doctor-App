package com.doctor.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.repo.AppointmentRepo;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.PatientRepo;

@Service
public class AppointmentServiceImpl implements IAppointmentService {


	@Autowired
	AppointmentRepo appointmentRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private PatientRepo patientRepo;


//	@Autowired
	//private Appointment appointment;

//	@Override
//	public Appointment saveAppointment(String remarks, String date, int doctorId) {
//		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (!(appointmentRepo.findAll().stream()
//				.anyMatch(a -> a.getPatient().getEmail().equals(loggedUser.getUsername())||a.getDoctor().getDoctorId()==doctorId)))// this prevents overriding
//																							// the recent requested
//																							// patient due to one to one
//																							// relation
//		{
//			Optional<Doctor> doctorOpt = doctorRepo.findById(Integer.valueOf(doctorId));
//			Optional<Patient> patientOpt = patientRepo.findByEmail(loggedUser.getUsername());
//			appointment.setDoctor(doctorOpt.get());
//			appointment.setPatient(patientOpt.get());
//			LocalDate parsedDate = LocalDate.parse(date);
//			appointment.setAppointmentDate(parsedDate);
//			appointment.setRemark(remarks);
//			appointment.setStatus("PENDING");
//			return appointmentRepo.save(appointment);
//		}
//		return appointment;
//	}
	
	
	
	@Override
	public ResponseEntity<HttpStatus> saveAppointment(Appointment appointment) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(appointmentRepo.findAll().stream()
				.anyMatch(a -> a.getPatient().getEmail().equals(loggedUser.getUsername()))))//this prevents overriding
																							// the recent requested
																							// patient due to one to one
																							// relation
		{
			Optional<Doctor> doctorOpt = doctorRepo.findById(Integer.valueOf(appointment.getDoctor().getDoctorId()));
			Optional<Patient> patientOpt = patientRepo.findByEmail(loggedUser.getUsername());
			appointment.setDoctor(doctorOpt.get());
			appointment.setPatient(patientOpt.get());
			LocalDate parsedDate = LocalDate.parse(String.valueOf(appointment.getAppointmentDate()));
			appointment.setAppointmentDate(parsedDate);
			appointment.setRemark(appointment.getRemark());
			appointment.setStatus("PENDING");
			appointmentRepo.save(appointment);
			
			return ResponseEntity.ok(HttpStatus.ACCEPTED); 
		}
		return ResponseEntity.of(Optional.of(HttpStatus.ALREADY_REPORTED));
	}

	@Override
	public List<Appointment> getAllAppointments() {
		
		List<Appointment> li=appointmentRepo.findAll();
		System.out.println(li);
		return li;
	}

	@Override
	public Appointment getAppointment(int appointmentId) {
		return appointmentRepo.findById(appointmentId).orElseThrow();
	}

	@Override
	public Appointment deleteAppointment(int appointmentId) {
		Appointment appointment= appointmentRepo.findById(appointmentId).orElseThrow();
		appointmentRepo.deleteById(appointmentId);
		return appointment;
	}

	@Override
	public Appointment updateAppointment(Map<String,Object> updates) {
		Appointment actualAppointment=appointmentRepo.findById(Integer.valueOf(String.valueOf(updates.get("appointmentId")))).get();
		
		if(updates.containsKey("status"))
			actualAppointment.setStatus(String.valueOf(updates.get("status")));
		if(updates.containsKey("remark")) {
			actualAppointment.setRemark(String.valueOf(updates.get("remark")));
		}
		if(updates.containsKey("appointmentDate"))
			actualAppointment.setAppointmentDate(LocalDate.parse(String.valueOf((updates.get("appointmentDate")))));
		if(updates.containsKey("doctor"))
			actualAppointment.setDoctor(((Doctor)(updates.get("doctor"))));
		if(updates.containsKey("patient"))
			actualAppointment.setPatient((Patient)(updates.get("patient")));
		
		
		
		
		return appointmentRepo.save(actualAppointment);
	}
	public Appointment acceptAppointment(Appointment appointment) {
		return appointmentRepo.save(appointment);
	}

	@Override
	public List<Appointment> getAppointments(Doctor doctor) {
		return appointmentRepo.findByDoctor(doctor);
	}

	@Override
	public List<Appointment> getAppointments(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

}
