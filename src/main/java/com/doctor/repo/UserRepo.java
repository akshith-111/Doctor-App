package com.doctor.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.doctor.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	//UserDetails findByUsername(String username);
	Optional<UserDetails> findByUsername(String username);

	
}
