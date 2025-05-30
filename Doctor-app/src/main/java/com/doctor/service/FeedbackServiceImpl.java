package com.doctor.service;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doctor.dto.FeedbackDTO;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.repo.FeedbackRepo;
import com.doctor.repo.PatientRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements IFeedbackService {

	private final FeedbackRepo feedbackRepo;
	
	private final PatientRepo patientRepo;
		
	private final ModelMapper modelMapper;
	
	@Override
	public FeedbackDTO addFeedback(Feedback feedback) {
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patient=patientRepo.findByEmail(user.getUsername())
				.orElseThrow(()->new ResourceNotFoundException("No Data Found On the id: "+user.getUsername()));
		Doctor doctor=patient.getAppointment().getDoctor();
		feedback.setDoctor(doctor);
		feedback.setPatient(patient);
		feedback=feedbackRepo.save(feedback);
		FeedbackDTO feedbackDTO=modelMapper.map(feedback, FeedbackDTO.class);
		return feedbackDTO;
	}

	@Override
	public FeedbackDTO getFeedback(Feedback feedback) {
		feedback= feedbackRepo.findById(feedback.getFeedbackId()).get();
		FeedbackDTO feedbackDTO=modelMapper.map(feedback, FeedbackDTO.class);
		return feedbackDTO;
	}

	
	@Override
	public List<FeedbackDTO> getAllFeedbacks(Doctor doctor) {
		
		List<Feedback> feedbackList=feedbackRepo.findByDoctor(doctor);
		List<FeedbackDTO> feedbackDTO=feedbackList.stream().map(f->modelMapper.map(f, FeedbackDTO.class)).toList();

		return feedbackDTO;
	}
	
	

}
