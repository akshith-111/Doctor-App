package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doctor.entity.AvailabilityDates;

import jakarta.transaction.Transactional;

public interface AvailabilityDatesRepo extends JpaRepository<AvailabilityDates, Integer> {

	@Modifying
	@Transactional
	@Query(value="delete from availability_dates where doctor_doctor_id=?1",nativeQuery = true)
	void deleteByDoctorId(int doctorId);
	
	

}
