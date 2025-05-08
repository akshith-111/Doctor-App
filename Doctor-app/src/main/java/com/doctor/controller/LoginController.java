package com.doctor.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.User;
import com.doctor.repo.UserRepo;

@RestController
public class LoginController {

	@Autowired
	private UserRepo repo;
	

	@GetMapping("/login")
	public String login() {
		return "login success";
	}
	
	
	
	
	
	
	
	@GetMapping("/get/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getUser(@PathVariable int id) {
		Optional<User> opt= repo.findById(id);
		
		return opt.get();
	}
}
