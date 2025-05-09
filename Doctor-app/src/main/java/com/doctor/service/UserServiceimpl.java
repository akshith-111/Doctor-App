package com.doctor.service;

import java.util.Optional;

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
	private UserRepo userRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private PatientRepo patientRepo;

	@Override
	public User addUser(User user) {
		
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepo.findByUsername(username);
	}

	@Override
	public User validateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User removeUser(User user) {
		Optional<User> opt=userRepo.findById(user.getUserId());
		opt.orElseThrow();
		userRepo.delete(user);
		return opt.get();
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}


	
}
