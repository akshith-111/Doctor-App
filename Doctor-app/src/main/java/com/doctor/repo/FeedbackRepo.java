package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.entity.Doctor;
import com.doctor.entity.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

//	List<Feedback> findbyDoctor(int id);

}
