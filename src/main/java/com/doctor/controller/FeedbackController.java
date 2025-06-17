package com.doctor.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.FeedbackDTO;
import com.doctor.model.ApiResponse;
import com.doctor.model.FeedbackModel;
import com.doctor.service.IFeedbackService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private IFeedbackService feedbackservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addFeedback(@Valid @RequestBody FeedbackModel feedbackModel) {
		
		Optional<FeedbackDTO> opt=feedbackservice.addFeedback(feedbackModel);
		
		if(opt.isPresent())
			return ResponseEntity.ok(opt.get());
		
		return ResponseEntity.ok(new ApiResponse("SUCCESS","CANNOT ADD FEEDBACK"));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<FeedbackDTO> getFeedback(@PathVariable int id) {
		return ResponseEntity.ok(feedbackservice.getFeedback(id));
	}
	
	
}
