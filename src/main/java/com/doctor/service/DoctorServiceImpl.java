package com.doctor.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.dto.AvailabilityDatesDTO;
import com.doctor.dto.DoctorDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.model.DoctorModel;
import com.doctor.repo.AvailabilityDatesRepo;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {


	private final DoctorRepo doctorRepo;

	
	private final UserRepo userRepo;

	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	
	private final AvailabilityDatesRepo availabilityDatesRepo;


	private final ModelMapper mapper;


	@Override
	public DoctorDTO saveDoctor(DoctorModel doctorModel) {
		Doctor doctor=mapper.map(doctorModel, Doctor.class);
		
		doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		User user=new User();
		user.setPassword(doctor.getPassword());
		user.setRole("DOCTOR");
		user.setUsername(doctor.getEmail());
		userRepo.save(user);
		doctor=doctorRepo.save(doctor);
		DoctorDTO doctorDTO=mapper.map(doctor, DoctorDTO.class);
		System.out.println(doctorDTO);
		return doctorDTO;
	}

	@Override
	public DoctorDTO updateDoctor(DoctorModel doctorModel) {
		Doctor doctor=mapper.map(doctorModel, Doctor.class);
		
		Doctor actualDoctor =doctorRepo.findById(doctor.getDoctorId())
				.orElseThrow(()->new ResourceNotFoundException("No Data Found on this Id: "+doctor.getDoctorId()));
		doctor.setAppointments(actualDoctor.getAppointments());
		doctor.setAvailabilityDates(actualDoctor.getAvailabilityDates());
		doctor.setFeedback(actualDoctor.getFeedback());
		System.out.println(doctor.getAppointments().get(0).getAppointmentId());
		doctorRepo.save(doctor);
		DoctorDTO doctorDTO=mapper.map(doctor,DoctorDTO.class);
		return doctorDTO;
	}

	@Override
	public List<DoctorDTO> getDoctorList() {

		List<Doctor> doctorList=doctorRepo.findAll();
		List<DoctorDTO> dtoList=doctorList.stream().map(d->mapper.map(d, DoctorDTO.class)).toList();
		return dtoList;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public AvailabilityDatesDTO addAvailability(AvailabilityDatesDTO availabilityDatesDTO) {
		AvailabilityDates availabilityDates=mapper.map(availabilityDatesDTO, AvailabilityDates.class);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Doctor doctor = doctorRepo.findByEmail(user.getUsername());
		availabilityDates.setDoctor(doctor);
		availabilityDates= availabilityDatesRepo.save(availabilityDates);
		
		availabilityDatesDTO=mapper.map(availabilityDates, AvailabilityDatesDTO.class);
		return availabilityDatesDTO;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public AvailabilityDatesDTO updateAvailability(AvailabilityDatesDTO availabilityDatesDTO) {
		AvailabilityDates availabilityDates=mapper.map(availabilityDatesDTO, AvailabilityDates.class);
		
		AvailabilityDates actualAvailabilityDates = availabilityDatesRepo
				.findById(availabilityDates.getAvailabilityId())
				.orElseThrow(()->new ResourceNotFoundException("No Data Found on this Id: "+availabilityDates.getAvailabilityId()));

		actualAvailabilityDates.setEndDate(availabilityDates.getEndDate());
		actualAvailabilityDates.setFromDate(availabilityDates.getFromDate());
		
		availabilityDatesDTO=mapper.map(actualAvailabilityDates, AvailabilityDatesDTO.class);
		return availabilityDatesDTO;
	}

	@Override
	public ResponseEntity<DoctorDTO> getDoctor(int id) {

		Doctor doctor = doctorRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No Data Found on this Id: "+id));
		DoctorDTO doctorDTO=mapper.map(doctor, DoctorDTO.class);
		//System.out.println(doctor.getFeedback().get(0));
		return new ResponseEntity<DoctorDTO>(doctorDTO,HttpStatus.OK);
	}

	@Override
	@Transactional
	public DoctorDTO removeDoctor(DoctorModel doctorModel) {
		Doctor doctor=mapper.map(doctorModel, Doctor.class);
		
		int id=doctor.getDoctorId();
		doctor = doctorRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("No Data Found on this Id: "+id));

		List<Appointment> appointments = doctor.getAppointments();
		List<Feedback> feedback  = doctor.getFeedback();

		if(appointments!=null) {
			appointments.forEach(appo->{
				Patient patient= appo.getPatient();
						patient.setAppointment(null);
			});
		}

		if (feedback != null) {
			feedback.forEach(f->{
				f.getPatient().setFeedback(null);
				f.setPatient(null);
				f.setDoctor(null);
			});
		}
		
		doctorRepo.delete(doctor);
		userRepo.delete((User) userRepo.findByUsername(doctor.getEmail()));
		
		DoctorDTO doctorDTO=mapper.map(doctor, DoctorDTO.class);
		
		return doctorDTO;
	}

	@Override
	public List<DoctorDTO> getDoctorListBySpeciality(String speciality) {
		List<Doctor> doctorList= doctorRepo.findBySpeciality(speciality);
		List<DoctorDTO> dtoList= doctorList.stream().map(d->mapper.map(d, DoctorDTO.class)).toList();
		return dtoList;
	}

}
