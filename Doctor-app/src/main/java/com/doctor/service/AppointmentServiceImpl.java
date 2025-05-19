package com.doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.doctor.controller.AdminController;
import com.doctor.dto.AppointmentDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.repo.AppointmentRepo;
import com.doctor.repo.AvailabilityDatesRepo;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.PatientRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements IAppointmentService {

    
	private final AppointmentRepo appointmentRepo;

	private final DoctorRepo doctorRepo;

	private final PatientRepo patientRepo;
	
	private final ModelMapper mapper;


  	@Override
	public Optional<AppointmentDTO> saveAppointment(Appointment appointment) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(appointmentRepo.findAll().stream()
				.anyMatch(a -> a.getPatient().getEmail().equals(loggedUser.getUsername()))))//this prevents overriding
																							// the recent requested
																							// patient due to one to one
																							// relation
		{
			Optional<Doctor> doctorOpt = doctorRepo.findById(Integer.valueOf(appointment.getDoctor().getDoctorId()));
			Optional<Patient> patientOpt = patientRepo.findByEmail(loggedUser.getUsername());
			appointment.setDoctor(doctorOpt.orElseThrow(()->new ResourceNotFoundException("No Data Found on This Id : "+appointment.getDoctor().getDoctorId())));
			appointment.setPatient(patientOpt.orElseThrow(()->new ResourceNotFoundException("No Data Found on This Id : "+loggedUser.getUsername())));
			LocalDate parsedDate = LocalDate.parse(String.valueOf(appointment.getAppointmentDate()));
			appointment.setAppointmentDate(parsedDate);
			appointment.setRemark(appointment.getRemark());
			appointment.setStatus("PENDING");
			AppointmentDTO appointmentDTO= mapper.map(appointmentRepo.save(appointment),AppointmentDTO.class);
			
			return Optional.of(appointmentDTO);
			//return new ResponseEntity<AppointmentDTO>(appointmentDTO,HttpStatus.OK); 
		}
		return Optional.empty();
		//return new ResponseEntity<AppointmentDTO>(HttpStatus.ALREADY_REPORTED);
	}

	@Override
	public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
		
		List<Appointment> li=appointmentRepo.findAll();
		List<AppointmentDTO> dtoList= li.stream().map(a->mapper.map(a, AppointmentDTO.class)).toList();
		return new ResponseEntity<List<AppointmentDTO>>(dtoList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AppointmentDTO> getAppointment(int appointmentId) {
		
		Appointment app= appointmentRepo.findById(appointmentId).orElseThrow(()->new ResourceNotFoundException("No Data Found on This Id : "+appointmentId));
		AppointmentDTO appointmentDto=mapper.map(app, AppointmentDTO.class);
		return new ResponseEntity<AppointmentDTO>(appointmentDto,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AppointmentDTO> deleteAppointment(int appointmentId) {
		Appointment appointment= appointmentRepo.findById(appointmentId).orElseThrow(()->new ResourceNotFoundException("No Data Found on This Id : "+appointmentId));
		List<Appointment> list= appointment.getDoctor().getAppointments();
		Appointment appointmentToBeRemoved= list.stream().filter(a->a.getAppointmentId()==appointmentId).toList().get(0);
		list.remove(appointmentToBeRemoved);
		appointment.getPatient().setAppointment(null);
		appointmentRepo.deleteById(appointmentId);
		AppointmentDTO appointmentDTO =mapper.map(appointment, AppointmentDTO.class);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AppointmentDTO> updateAppointment(Map<String,Object> updates) {
		Appointment actualAppointment=appointmentRepo.findById(Integer.valueOf(String.valueOf(updates.get("appointmentId")))).orElseThrow(()->new ResourceNotFoundException("No Data Found on This Id : "+updates.get("appointmentId")));
		
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
		
		Appointment appointment=appointmentRepo.save(actualAppointment);
		AppointmentDTO appointmentDTO= mapper.map(appointment, AppointmentDTO.class);
		
		
		return new ResponseEntity<AppointmentDTO>(appointmentDTO,HttpStatus.OK) ;
	}
	public Appointment acceptAppointment(Appointment appointment) {
		
		return appointmentRepo.save(appointment);
	}

	@Override
	public List<AppointmentDTO> getAppointments(Doctor doctor) {
		
		List<Appointment> appointmentList=appointmentRepo.findByDoctor(doctor);
		List<AppointmentDTO> dtoList=appointmentList.stream().map(a->mapper.map(a, AppointmentDTO.class)).toList();
		return dtoList;
	}

	@Override
	public List<AppointmentDTO> getAppointments(LocalDate date) {
		
		List<Appointment> appointmentList=appointmentRepo.findByAppointmentDate(date);
		List<AppointmentDTO> dtoList= appointmentList.stream().map(a->mapper.map(a, AppointmentDTO.class)).toList();
		
		return dtoList;
	}

	@Override
	public AppointmentDTO getAppointment(Patient patient) {
		AppointmentDTO appointmentDTO=mapper.map(appointmentRepo.findByPatient(patient),AppointmentDTO.class);
		return appointmentDTO;
	}

}
