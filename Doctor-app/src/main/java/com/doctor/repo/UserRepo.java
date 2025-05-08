package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.doctor.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	UserDetails findByUsername(String username);
	
}
