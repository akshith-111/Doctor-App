package com.doctor.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer>{

	Optional<Patient> findByEmail(String username);

	

}
