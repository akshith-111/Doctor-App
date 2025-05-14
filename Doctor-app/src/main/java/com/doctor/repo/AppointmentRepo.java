package com.doctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;

import jakarta.transaction.Transactional;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

	List<Appointment> findByDoctor(Doctor doctor);

	@Modifying
	@Transactional
	@Query(value="delete from appointment where doctor_id=?1",nativeQuery = true)
	void deleteByDoctorId(int id);
	
	
	
}
