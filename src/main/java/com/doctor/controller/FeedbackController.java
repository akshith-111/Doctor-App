package com.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.FeedbackDTO;
import com.doctor.model.FeedbackModel;
import com.doctor.service.IFeedbackService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private IFeedbackService feedbackservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<FeedbackDTO> addFeedback(@Valid @RequestBody FeedbackModel feedbackModel) {
		return ResponseEntity.ok(feedbackservice.addFeedback(feedbackModel));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<FeedbackDTO> getFeedback(@PathVariable int id) {
		return ResponseEntity.ok(feedbackservice.getFeedback(id));
	}
	
	
}
