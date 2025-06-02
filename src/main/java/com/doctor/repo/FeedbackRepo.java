package com.doctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {


	public List<Feedback> findByDoctor(Doctor doctor);


}
