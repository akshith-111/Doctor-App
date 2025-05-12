package com.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Feedback;
import com.doctor.service.IFeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private IFeedbackService feedbackservice;
	
	
	@PostMapping("/add")
	public Feedback addFeedback(@RequestBody Feedback feedback) {
		return feedbackservice.addFeedback(feedback);
	}
	
	@GetMapping("/get")
	public Feedback getFeedback(@RequestBody Feedback feedback) {
		return feedbackservice.getFeedback(feedback);
	}
}
