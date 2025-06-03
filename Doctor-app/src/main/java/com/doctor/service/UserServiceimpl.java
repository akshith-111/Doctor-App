package com.doctor.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserDetailsService, IUserService {

	
	private final UserRepo userRepo;
	
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
		opt.orElseThrow(()->new ResourceNotFoundException("No Data Found on this Id: "+user.getUserId()));
		userRepo.delete(user);
		return opt.get();
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}


	
}
