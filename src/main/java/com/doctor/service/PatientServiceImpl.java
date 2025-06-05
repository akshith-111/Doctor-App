package com.doctor.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.doctor.DoctorAppApplication;
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
import com.doctor.repo.PatientRepo;
import com.doctor.repo.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {


	private final AppointmentRepo appointmentRepo;
	
	private final PatientRepo patientRepo;

	private final UserRepo userRepo;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final ModelMapper mapper;


	@Override
	public PatientDTO savePatient(PatientModel patientModel) {
		Patient patient=mapper.map(patientModel, Patient.class);
		
		patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
		User user=new User();
		user.setPassword(patient.getPassword());
		user.setRole("PATIENT");
		user.setUsername(patient.getEmail());
		userRepo.save(user);
		patient=patientRepo.save(patient);
		PatientDTO patientDTO =mapper.map(patient, PatientDTO.class);
		return patientDTO;
	}

	@Override
	public PatientDTO updatePatient(PatientModel patientModel) {
		Patient patient=mapper.map(patientModel, Patient.class);
		
		Patient actualPatient=patientRepo.findById(patient.getPatientId()).orElseThrow();
		patient.setAppointment(actualPatient.getAppointment());
		patient.setFeedback(actualPatient.getFeedback());
		patient=patientRepo.save(patient);
		
		PatientDTO patientDto=mapper.map(patient, PatientDTO.class);
		return patientDto;
	}

	@Override
	@Transactional
	public PatientDTO removePatient(int id) {
		System.out.println("Entered........");
		Patient patient = patientRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No Data Found With id : "+id));
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
		
		System.out.println("About to Delete");
		patientRepo.deleteById(patient.getPatientId());
		System.out.println("DELETED");
		PatientDTO patientDTO=mapper.map(patient, PatientDTO.class);
		return patientDTO;

	}

	@Override
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public PatientDTO getPatient(int id) {
				
		Patient patient = patientRepo.findById(id).orElseThrow();
		PatientDTO patientDTO=mapper.map(patient, PatientDTO.class);
		return patientDTO;
	}

	@Override
	public List<PatientDTO>getAllPatients() {

		List<Patient> patientList=patientRepo.findAll();
		List<PatientDTO> dtoList=patientList.stream().map(p->mapper.map(p, PatientDTO.class)).toList();
		return dtoList;
	}

	@Override
	public List<PatientDTO> getPatientListByDoctor(DoctorModel doctorModel) {
		Doctor doctor=mapper.map(doctorModel, Doctor.class);
		
		List<Appointment> appointmentList =appointmentRepo.findByDoctor(doctor);
		List<PatientDTO> patientDto= appointmentList.stream().map(a->mapper.map(a.getPatient(), PatientDTO.class)).toList();
					
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
		
			patient=patientRepo.save(patient);
			PatientDTO patientDto=mapper.map(patient, PatientDTO.class);
		return patientDto;
	}

}
