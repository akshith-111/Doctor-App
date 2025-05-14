package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doctor.entity.Feedback;

import jakarta.transaction.Transactional;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

	@Modifying
	@Transactional
	@Query(value="DELETE FROM feedback WHERE doctor_doctor_id=:id",nativeQuery = true)
	void deleteByDoctorId(@Param("id") int doctorId);

//	List<Feedback> findbyDoctor(int id);

}
