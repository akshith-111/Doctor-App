package com.doctor.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.doctor.dto.FeedbackDTO;
import com.doctor.entity.Feedback;
import com.doctor.model.DoctorModel;
import com.doctor.model.FeedbackModel;

@Service
public interface IFeedbackService {
	public FeedbackDTO addFeedback(FeedbackModel feedbackModel);
	public FeedbackDTO getFeedback(int id);
	public List<FeedbackDTO> getAllFeedbacks(DoctorModel doctorModel);

}
