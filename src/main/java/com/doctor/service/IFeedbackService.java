package com.doctor.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.doctor.dto.FeedbackDTO;
import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;

@Service
public interface IFeedbackService {
	public FeedbackDTO addFeedback(Feedback feedback);
	public FeedbackDTO getFeedback(Feedback feedback);
	public List<FeedbackDTO> getAllFeedbacks(Doctor doctor);

}
