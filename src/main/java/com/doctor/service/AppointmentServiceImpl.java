package com.doctor.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.doctor.dto.AppointmentDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.AvailabilityDates;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.model.AppointmentModel;
import com.doctor.model.DoctorModel;
import com.doctor.model.PatientModel;
import com.doctor.repo.AppointmentRepo;
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
	public Optional<AppointmentDTO> saveAppointment(AppointmentModel appointmentModel) {
		Appointment appointment = mapper.map(appointmentModel, Appointment.class);
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Patient> retrivedPatient=new ArrayList<>();
		Optional<Doctor> doctorOpt = doctorRepo.findById(appointment.getDoctor().getDoctorId());
		AvailabilityDates availAvailabilityDates=doctorOpt.get().getAvailabilityDates();
		LocalDate appointmentDate=appointment.getAppointmentDate();
		
		if((appointmentDate.isBefore(availAvailabilityDates.getEndDate())||appointmentDate.isEqual(availAvailabilityDates.getEndDate()))
				&&(appointmentDate.isAfter(availAvailabilityDates.getFromDate())||appointmentDate.isEqual(availAvailabilityDates.getFromDate()))) {
		if (appointmentRepo.findAll().stream()
				.anyMatch(a -> {
					if(a.getPatient().getEmail().equals(loggedUser.getUsername())){
						retrivedPatient.add(a.getPatient());
						return true;
					}
					return false;
					
				}))//checking if appointment already exists
		{
			Patient patient=retrivedPatient.get(0);
			
			Appointment retrivedAppointment= patient.getAppointment();
			if(retrivedAppointment.getStatus().equals("ACCEPTED")) {
				return Optional.empty();
			}
			if(retrivedAppointment.getStatus().equals("REJECTED")) {
				retrivedAppointment.setStatus("PENDING");
				System.out.println("------------------------------"+appointment.getDoctor().getDoctorName()+"========================");
				retrivedAppointment.setDoctor(appointment.getDoctor());
				retrivedAppointment.setDoctor(doctorRepo.findById(appointment.getDoctor().getDoctorId()).orElseThrow(() -> new ResourceNotFoundException(
						"No Data Found on This Id : " + appointment.getDoctor().getDoctorId())));
				
				retrivedAppointment.setAppointmentDate(appointment.getAppointmentDate());
				retrivedAppointment.setRemark(appointment.getRemark());
			
				AppointmentDTO appointmentDTO = mapper.map(appointmentRepo.save(retrivedAppointment), AppointmentDTO.class);
				System.out.println("DcotorDTO :"+appointmentDTO.getDoctor().getDoctorName()+"    doctor :"+appointment.getDoctor().getDoctorName());
				return Optional.of(appointmentDTO);
				
			}else
				return Optional.empty();

			
		}
			
			
			Optional<Patient> patientOpt = patientRepo.findByEmail(loggedUser.getUsername());
			appointment.setDoctor(doctorOpt.orElseThrow(() -> new ResourceNotFoundException(
					"No Data Found on This Id : " + appointment.getDoctor().getDoctorId())));
			appointment.setPatient(patientOpt.orElseThrow(
					() -> new ResourceNotFoundException("No Data Found on This Id : " + loggedUser.getUsername())));
			LocalDate parsedDate = LocalDate.parse(String.valueOf(appointment.getAppointmentDate()));
			appointment.setAppointmentDate(parsedDate);
			appointment.setRemark(appointment.getRemark());
			appointment.setStatus("PENDING");
			AppointmentDTO appointmentDTO = mapper.map(appointmentRepo.save(appointment), AppointmentDTO.class);

			return Optional.of(appointmentDTO);
		}
		return Optional.empty();
	}

	@Override
	public List<AppointmentDTO> getAllAppointments() {

		List<Appointment> li = appointmentRepo.findAll();
		List<AppointmentDTO> dtoList = li.stream().map(a -> mapper.map(a, AppointmentDTO.class)).toList();
		return dtoList;
	}

	@Override
	public AppointmentDTO getAppointment(int appointmentId) {

		Appointment app = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found on This Id : " + appointmentId));
		AppointmentDTO appointmentDto = mapper.map(app, AppointmentDTO.class);
		return appointmentDto;
	}

	@Override
	public AppointmentDTO deleteAppointment(int appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("No Data Found on This Id : " + appointmentId));
		List<Appointment> list = appointment.getDoctor().getAppointments();
		Appointment appointmentToBeRemoved = list.stream().filter(a -> a.getAppointmentId() == appointmentId).toList()
				.get(0);
		list.remove(appointmentToBeRemoved);
		appointment.getPatient().setAppointment(null);
		appointmentRepo.deleteById(appointmentId);
		AppointmentDTO appointmentDTO = mapper.map(appointment, AppointmentDTO.class);
		return appointmentDTO;
	}

	@Override
	public Optional<AppointmentDTO> updateAppointment(Map<String, Object> updates) {
		Appointment actualAppointment = appointmentRepo
				.findById(Integer.valueOf(String.valueOf(updates.get("appointmentId"))))
				.orElseThrow(() -> new ResourceNotFoundException(
						"No Data Found on This Id : " + updates.get("appointmentId")));

		User loggedInuser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (actualAppointment.getDoctor().getEmail().equals(loggedInuser.getUsername())
				|| loggedInuser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {

			System.out.println("doctor: " + actualAppointment.getDoctor().getEmail() + " LoggedUser :"
					+ loggedInuser.getUsername());
			System.out.println("APPOINTMENT STATUS : "+actualAppointment.getStatus() );
			
			
			
			if (updates.containsKey("status")) {
				if ((!actualAppointment.getStatus().equals("REJECTED"))) {
					LocalDate date=actualAppointment.getAppointmentDate();
					if(date.isAfter(LocalDate.now())||date.isEqual(LocalDate.now()))
					{
						System.out.println("Today :"+LocalDate.now()+" appointmentDate :"+date);
						actualAppointment.setStatus(String.valueOf(updates.get("status")));
					}else return Optional.empty();
				} else
					return Optional.empty();
			}
			if (updates.containsKey("remark")) {
				actualAppointment.setRemark(String.valueOf(updates.get("remark")));
			}
			if (updates.containsKey("appointmentDate"))
				actualAppointment.setAppointmentDate(LocalDate.parse(String.valueOf((updates.get("appointmentDate")))));
			if (updates.containsKey("doctor"))
				actualAppointment.setDoctor(((Doctor) (updates.get("doctor"))));
			if (updates.containsKey("patient"))
				actualAppointment.setPatient((Patient) (updates.get("patient")));

			Appointment appointment = appointmentRepo.save(actualAppointment);
			AppointmentDTO appointmentDTO = mapper.map(appointment, AppointmentDTO.class);


			return Optional.of(appointmentDTO);
		}
		return Optional.empty();
	}

	public Appointment acceptAppointment(Appointment appointment) {

		return appointmentRepo.save(appointment);
	}

	@Override
	public List<AppointmentDTO> getAppointments(DoctorModel doctorModel) {
		Doctor doctor = mapper.map(doctorModel, Doctor.class);
		List<Appointment> appointmentList = appointmentRepo.findByDoctor(doctor);
		List<AppointmentDTO> dtoList = appointmentList.stream()
				.map(appointment -> mapper.map(appointment, AppointmentDTO.class)).toList();
		return dtoList;
	}

	@Override
	public List<AppointmentDTO> getAppointments(LocalDate date) {

		List<Appointment> appointmentList = appointmentRepo.findByAppointmentDate(date);
		List<AppointmentDTO> dtoList = appointmentList.stream().map(a -> mapper.map(a, AppointmentDTO.class)).toList();

		return dtoList;
	}

	@Override
	public AppointmentDTO getAppointment(PatientModel patientModel) {
		Patient patient = mapper.map(patientModel, Patient.class);
		AppointmentDTO appointmentDTO = mapper.map(appointmentRepo.findByPatient(patient), AppointmentDTO.class);
		return appointmentDTO;
	}

}
