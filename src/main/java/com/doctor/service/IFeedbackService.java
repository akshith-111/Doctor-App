package com.doctor.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doctor.dto.FeedbackDTO;
import com.doctor.model.DoctorModel;
import com.doctor.model.FeedbackModel;

@Service
public interface IFeedbackService {
	public Optional<FeedbackDTO> addFeedback(FeedbackModel feedbackModel);
	public FeedbackDTO getFeedback(int id);
	public List<FeedbackDTO> getAllFeedbacks(DoctorModel doctorModel);

}
