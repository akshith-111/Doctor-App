package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.dto.AvailabilityDatesDTO;
import com.doctor.dto.DoctorDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.Roles;
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
	// @Transactional
	public DoctorDTO saveDoctor(DoctorModel doctorModel) {
		Doctor doctor = mapper.map(doctorModel, Doctor.class);
		String email = doctor.getEmail();
		Optional<UserDetails> existingUser = userRepo.findByUsername(email);

		if (existingUser.isPresent()) {
			User user = (User) existingUser.get();
//			user.getRoles().forEach(role->{
//				role.getRole().equals("ROLE_DOCTOR");
//			});

			doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
			doctor = doctorRepo.save(doctor);
			DoctorDTO doctorDTO = mapper.map(doctor, DoctorDTO.class);
			user.getRoles().add(new Roles("ROLE_DOCTOR", user));
			userRepo.save(user);
			System.out.println(doctorDTO);
			return doctorDTO;

		}

		doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		User user = new User();
		user.setPassword(doctor.getPassword());
		// user.setRole("DOCTOR");
		user.getRoles().add(new Roles("ROLE_DOCTOR", user));
		user.setUsername(doctor.getEmail());
		userRepo.save(user);
		doctor = doctorRepo.save(doctor);
		DoctorDTO doctorDTO = mapper.map(doctor, DoctorDTO.class);
		System.out.println(doctorDTO);
		return doctorDTO;
	}

	@Override
	public DoctorDTO updateDoctor(DoctorModel doctorModel) {
		Doctor doctor = mapper.map(doctorModel, Doctor.class);
		System.out.println("Entered");
		Doctor actualDoctor = doctorRepo.findById(doctor.getDoctorId())
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found on this Id: " + doctor.getDoctorId()));
		doctor.setAppointments(actualDoctor.getAppointments());
		doctor.setAvailabilityDates(actualDoctor.getAvailabilityDates());
		doctor.setFeedback(actualDoctor.getFeedback());
		doctorRepo.save(doctor);
		DoctorDTO doctorDTO = mapper.map(doctor, DoctorDTO.class);
		return doctorDTO;
	}

	@Override
	public List<DoctorDTO> getDoctorList() {

		List<Doctor> doctorList = doctorRepo.findAll();
		List<DoctorDTO> dtoList = doctorList.stream().map(d -> mapper.map(d, DoctorDTO.class)).toList();
		return dtoList;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public AvailabilityDatesDTO addAvailability(AvailabilityDatesDTO availabilityDatesDTO) {
		AvailabilityDates availabilityDates = mapper.map(availabilityDatesDTO, AvailabilityDates.class);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Doctor doctor = doctorRepo.findByEmail(user.getUsername());
		availabilityDates.setDoctor(doctor);
		availabilityDates = availabilityDatesRepo.save(availabilityDates);

		availabilityDatesDTO = mapper.map(availabilityDates, AvailabilityDatesDTO.class);
		return availabilityDatesDTO;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	public Optional<AvailabilityDatesDTO> updateAvailability(AvailabilityDatesDTO availabilityDatesDTO) {
		AvailabilityDates availabilityDates = mapper.map(availabilityDatesDTO, AvailabilityDates.class);

		int doctorId = availabilityDatesDTO.getDoctorId();

		
		Doctor doctor = doctorRepo.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found on this Id: " + doctorId));

		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		AvailabilityDates actualAvailabilityDates = doctor.getAvailabilityDates();
		if ((doctor.getAppointments().isEmpty() || doctor.getAppointments() == null)
				&& doctor.getEmail().equals(user.getUsername())) {

			
			actualAvailabilityDates.setEndDate(availabilityDates.getEndDate());
			actualAvailabilityDates.setFromDate(availabilityDates.getFromDate());
			System.out.println("entered");
			availabilityDatesDTO = mapper.map(actualAvailabilityDates, AvailabilityDatesDTO.class);
			doctorRepo.save(doctor);
			return Optional.of(availabilityDatesDTO);
		}
		return Optional.empty();
	}

	@Override
	public ResponseEntity<DoctorDTO> getDoctor(int id) {

		Doctor doctor = doctorRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found on this Id: " + id));
		DoctorDTO doctorDTO = mapper.map(doctor, DoctorDTO.class);

		return new ResponseEntity<DoctorDTO>(doctorDTO, HttpStatus.OK);
	}

	@Override
	// @Transactional
	public DoctorDTO removeDoctor(int id) {

		Doctor doctor = doctorRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found on this Id: " + id));

		List<Appointment> appointments = doctor.getAppointments();
		List<Feedback> feedback = doctor.getFeedback();

		if (appointments != null) {
			appointments.forEach(appo -> {
				Patient patient = appo.getPatient();
				patient.setAppointment(null);
			});
		}

		if (feedback != null) {
			feedback.forEach(f -> {
				f.getPatient().setFeedback(null);
				f.setPatient(null);
				f.setDoctor(null);
			});
		}

		// doctor.getPatients().stream().forEach(patient->patient.setDoctors(null));

		doctor.getTreatmentHistory().stream().forEach(t -> {
			t.setDoctor(null);
			t.setPatient(null);
		});

		doctorRepo.delete(doctor);

		userRepo.delete((User) (userRepo.findByUsername(doctor.getEmail()).get()));

		DoctorDTO doctorDTO = mapper.map(doctor, DoctorDTO.class);

		return doctorDTO;
	}

	@Override
	public List<DoctorDTO> getDoctorListBySpeciality(String speciality) {
		List<Doctor> doctorList = doctorRepo.findBySpeciality(speciality);
		List<DoctorDTO> dtoList = doctorList.stream().map(d -> mapper.map(d, DoctorDTO.class)).toList();
		return dtoList;
	}

}
