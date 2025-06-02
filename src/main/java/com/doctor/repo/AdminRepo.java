package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	Admin findByEmail(String email);

}
