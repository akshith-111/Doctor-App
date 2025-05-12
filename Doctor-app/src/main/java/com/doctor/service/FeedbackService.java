package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;
import com.doctor.entity.Patient;
import com.doctor.entity.User;
import com.doctor.repo.DoctorRepo;
import com.doctor.repo.FeedbackRepo;
import com.doctor.repo.PatientRepo;

@Service
public class FeedbackService implements IFeedbackService {

	@Autowired
	private FeedbackRepo feedbackRepo;
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Override
	public Feedback addFeedback(Feedback feedback) {
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patient=patientRepo.findByEmail(user.getUsername()).get();
		Doctor doctor=patient.getAppointment().getDoctor();
		feedback.setDoctor(doctor);
		feedback.setPatient(patient);
		return feedbackRepo.save(feedback);
	}

	@Override
	public Feedback getFeedback(Feedback feedback) {
		Optional<Feedback> opt= feedbackRepo.findById(feedback.getFeedbackId());
		return opt.get();
	}

	@Override
	public List<Feedback> getAllFeedbacks(Doctor doctor) {
		List<Feedback> li=feedbackRepo.findAll();
		li=li.stream().filter(f->f.getDoctor().getDoctorId()==doctor.getDoctorId()).toList();
		return li;
	}

}
