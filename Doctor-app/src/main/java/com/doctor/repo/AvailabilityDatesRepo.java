package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.entity.AvailabilityDates;

public interface AvailabilityDatesRepo extends JpaRepository<AvailabilityDates, Integer> {
	
	

}
