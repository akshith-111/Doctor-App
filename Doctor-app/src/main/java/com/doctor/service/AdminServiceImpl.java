package com.doctor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.entity.Admin;
import com.doctor.entity.User;
import com.doctor.repo.AdminRepo;
import com.doctor.repo.UserRepo;

@Service
public class AdminServiceImpl implements IAdminService {

    private final AuthenticationProvider authenticationProvider;


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private User user;//getting user object
	
	@Autowired
	private UserRepo userRepo;


    AdminServiceImpl(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    
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


	@Override
	public Admin udpateAdmin(Admin admin) {
			
		return adminRepo.save(admin);
	}


	@Override
	public Admin removeAdmin(Admin admin) {
		Optional<Admin> opt= adminRepo.findById(admin.getAdminId());
		opt.orElseThrow();
		adminRepo.delete(admin);
		return opt.get();
	}


	@Override
	public Admin viewAdmin(Admin admin) {
		Optional<Admin> opt=adminRepo.findById(admin.getAdminId());
		return opt.get();
	}

}
