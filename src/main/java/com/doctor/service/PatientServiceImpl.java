package com.doctor.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.dto.MiniPatientDTO;
import com.doctor.dto.PatientDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.model.DoctorModel;
import com.doctor.model.PatientModel;
import com.doctor.repo.AppointmentRepo;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.PatientRepo;
import com.doctor.repo.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {

	private final DoctorRepo doctorRepo;

	private final AppointmentRepo appointmentRepo;

	private final PatientRepo patientRepo;

	private final UserRepo userRepo;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final ModelMapper mapper;

	@Override
	public PatientDTO savePatient(PatientModel patientModel) {
		Patient patient = mapper.map(patientModel, Patient.class);

		patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
		User user = new User();
		user.setPassword(patient.getPassword());
		user.setRole("PATIENT");
		user.setUsername(patient.getEmail());
		userRepo.save(user);
		patient = patientRepo.save(patient);
		PatientDTO patientDTO = mapper.map(patient, PatientDTO.class);
		return patientDTO;
	}

	@Override
	public PatientDTO updatePatient(PatientModel patientModel) {
		Patient patient = mapper.map(patientModel, Patient.class);

		Patient actualPatient = patientRepo.findById(patient.getPatientId()).orElseThrow();
		patient.setAppointment(actualPatient.getAppointment());
		patient.setFeedback(actualPatient.getFeedback());
		patient = patientRepo.save(patient);

		PatientDTO patientDto = mapper.map(patient, PatientDTO.class);
		return patientDto;
	}

	@Override
	@Transactional
	public PatientDTO removePatient(int id) {
		System.out.println("Entered........");
		Patient patient = patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found With id : " + id));
		System.out.println(patient.getEmail());
		userRepo.delete((User) userRepo.findByUsername(patient.getEmail()));
		patient.getAppointment();

		Appointment appointment = patient.getAppointment();
		Feedback feedback = patient.getFeedback();

		if (appointment != null) {
			appointment.setPatient(null);
			appointment.getDoctor().setAppointments(null);
			appointment.setDoctor(null);

		}

		if (feedback != null) {

			feedback.setPatient(null);
			feedback.getDoctor().setFeedback(null);
			feedback.setDoctor(null);
		}

		patientRepo.deleteById(patient.getPatientId());
		PatientDTO patientDTO = mapper.map(patient, PatientDTO.class);
		return patientDTO;

	}

	@Override
	public PatientDTO getPatient(int id) {

		Patient patient = patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found With id : " + id));
		PatientDTO patientDTO = mapper.map(patient, PatientDTO.class);
		return patientDTO;
	}

	@Override
	public List<PatientDTO> getAllPatients() {

		List<Patient> patientList = patientRepo.findAll();
		List<PatientDTO> dtoList = patientList.stream().map(p -> mapper.map(p, PatientDTO.class)).toList();
		return dtoList;
	}

	@Override
	public List<PatientDTO> getPatientListByDoctor(DoctorModel doctorModel) {
		Doctor doctor = mapper.map(doctorModel, Doctor.class);

		List<Appointment> appointmentList = appointmentRepo.findByDoctor(doctor);
		List<PatientDTO> patientDto = appointmentList.stream().map(a -> mapper.map(a.getPatient(), PatientDTO.class))
				.toList();

		return patientDto;
	}

	@Override
	public Patient getPatientListByDate(LocalDate appDate) {
		return null;
	}

	@Override
	public PatientDTO patchUpdatePatient(Map<String, Object> updates) {

		Patient patient = patientRepo.findById(Integer.valueOf(String.valueOf(updates.get("patientId")))).get();

		if (updates.containsKey("patientName"))
			patient.setPatientName(String.valueOf(updates.get("patientName")));

		if (updates.containsKey("mobileNo"))
			patient.setMobileNo(String.valueOf(updates.get("mobileNo")));

		if (updates.containsKey("email"))
			patient.setEmail(String.valueOf((updates.get("email"))));

		if (updates.containsKey("bloodGroup"))
			patient.setBloodGroup(String.valueOf(updates.get("bloodGroup")));

		if (updates.containsKey("age"))
			patient.setAge(String.valueOf(updates.get("age")));

		if (updates.containsKey("address"))
			patient.setAddress(String.valueOf((updates.get("address"))));

		if (updates.containsKey("password"))
			patient.setPassword(bCryptPasswordEncoder.encode(String.valueOf(updates.get("password"))));

		if (updates.containsKey("appointment"))
			patient.setAppointment((Appointment) (updates.get("appointment")));

		if (updates.containsKey("feedback"))
			patient.setFeedback((Feedback) (updates.get("feedback")));

		patient = patientRepo.save(patient);
		PatientDTO patientDto = mapper.map(patient, PatientDTO.class);
		return patientDto;
	}

	@Override
	public Optional<List<MiniPatientDTO>> getAllPatientsHistory(LocalDate date, String doctorName) {
		List<Patient> patientList = new ArrayList<>();
				
		if (date == null && doctorName == null) {
			return Optional.empty();
		}

		if (doctorName == null) {
			patientList = patientRepo.findAll().stream()
					.filter(p ->{
						LocalDate appDate=p.getAppointment().getAppointmentDate();
						return (appDate.isBefore(date)||appDate.isEqual(date));	
					})
					.toList();
		}

		if (date == null) {
			List<Doctor> doctorList=doctorRepo.findByDoctorName(doctorName);
			if(doctorList.isEmpty()) {
				return Optional.empty();
			}else
				
			patientList = patientRepo.findByDoctors(doctorRepo.findByDoctorName(doctorName).get(0));
			
		}
		if(date!=null&&doctorName!=null) {
			List<Doctor> doctorList=doctorRepo.findByDoctorName(doctorName);
			if(doctorList.isEmpty()) {
				return Optional.empty();
			}
			else {
			patientList = patientRepo.findByDoctors(doctorList.get(0));
			patientList = patientList.stream()
					.filter(p -> p.getAppointment().getAppointmentDate().isBefore(date))
					.toList();
			}
		}

		if(patientList.isEmpty()) {
			return Optional.empty();
		}
		List<MiniPatientDTO> dtoList = patientList.stream()
				.map(patient -> mapper.map(patient, MiniPatientDTO.class)).toList();

		return Optional.of(dtoList);
	}

}
