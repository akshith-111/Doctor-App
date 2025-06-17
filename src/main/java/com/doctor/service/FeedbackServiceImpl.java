package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doctor.dto.FeedbackDTO;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.TreatmentHistory;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.model.DoctorModel;
import com.doctor.model.FeedbackModel;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.FeedbackRepo;
import com.doctor.repo.PatientRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements IFeedbackService {

	private final FeedbackRepo feedbackRepo;
	
	private final PatientRepo patientRepo;
		
	private final ModelMapper modelMapper;
	
	private final IAppointmentService appointmentService;
	
	private final DoctorRepo doctorRepo;
	@Override
	public Optional<FeedbackDTO> addFeedback(FeedbackModel feedbackModel) {
		Feedback feedback=modelMapper.map(feedbackModel, Feedback.class);
		
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patient=patientRepo.findByEmail(user.getUsername())
				.orElseThrow(()->new ResourceNotFoundException("No Data Found On the id: "+user.getUsername()));
		if(patient.getAppointment().getStatus().equals("ACCEPTED"))
		{
		Doctor doctor=patient.getAppointment().getDoctor();
		Feedback retrivedFeedback=feedbackRepo.findByPatient(patient);
		if(retrivedFeedback!=null) {
			retrivedFeedback.setDoctor(doctor);
			retrivedFeedback.setPatient(patient);
			retrivedFeedback.setFeedbackComment(feedback.getFeedbackComment());
			retrivedFeedback.setRating(feedback.getRating());
			feedback=feedbackRepo.save(retrivedFeedback);
		}else {
		feedback.setDoctor(doctor);
		feedback.setPatient(patient);
		feedback=feedbackRepo.save(feedback);
		}
		
		TreatmentHistory history=new TreatmentHistory(doctor,patient,patient.getAppointment().getAppointmentDate());
		doctor.getTreatmentHistory().add(history);
		doctorRepo.save(doctor);
		
		appointmentService.deleteAppointment(patient.getAppointment().getAppointmentId());
		
		FeedbackDTO feedbackDTO=modelMapper.map(feedback, FeedbackDTO.class);
		return Optional.of(feedbackDTO);
		}
		return Optional.empty();
	}

	@Override
	public FeedbackDTO getFeedback(int id) {
		
		
		Feedback feedback= feedbackRepo.findById(id).get();
		FeedbackDTO feedbackDTO=modelMapper.map(feedback, FeedbackDTO.class);
		return feedbackDTO;
	}

	
	@Override
	public List<FeedbackDTO> getAllFeedbacks(DoctorModel doctorModel) {
		Doctor doctor=modelMapper.map(doctorModel, Doctor.class);
		
		List<Feedback> feedbackList=feedbackRepo.findByDoctor(doctor);
		List<FeedbackDTO> feedbackDTO=feedbackList.stream().map(feedback->modelMapper.map(feedback, FeedbackDTO.class)).toList();

		return feedbackDTO;
	}
	
	

}
