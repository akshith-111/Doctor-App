package com.doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Admin;
import com.doctor.entity.Doctor;
import com.doctor.entity.User;
import com.doctor.repo.AdminRepo;
import com.doctor.repo.UserRepo;

@Service
public class AdminServiceImpl implements IAdminService {


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private User user;//getting user object
	
	@Autowired
	private UserRepo userRepo;

    
	@Override
	public Admin saveAdmin(Admin admin) {
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		admin=adminRepo.save(admin);
		user.setPassword(admin.getPassword());
		user.setRole("ADMIN");
		user.setUsername(admin.getEmail());
		userRepo.save(user);//saving user object
		return admin;
	}

}
