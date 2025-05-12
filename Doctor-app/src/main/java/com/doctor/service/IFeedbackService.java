package com.doctor.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;

@Service
public interface IFeedbackService {
	public Feedback addFeedback(Feedback feedback);
	public Feedback getFeedback(Feedback feedback);
	public List<Feedback> getAllFeedbacks(Doctor doctor);

}
