package com.doctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Integer>{

	Doctor findByEmail(String email);

	List<Doctor> findByDoctorName(String doctorName);
	
	List<Doctor> findBySpeciality(String speciality);

}
