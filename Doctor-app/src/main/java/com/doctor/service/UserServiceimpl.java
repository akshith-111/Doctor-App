package com.doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doctor.entity.User;
import com.doctor.repo.AdminRepo;
import com.doctor.repo.PatientRepo;
import com.doctor.repo.UserRepo;

@Service
public class UserServiceimpl implements UserDetailsService, IUserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private PatientRepo patientRepo;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repo.findByUsername(username);
	}


	
}
