package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.dto.AdminDTO;
import com.doctor.entity.Admin;
import com.doctor.entity.User;
import com.doctor.repo.AdminRepo;
import com.doctor.repo.UserRepo;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
    private ModelMapper modelMapper;



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


//	@Override
//	public Admin viewAdmin(Admin admin) {
//		Optional<Admin> opt=adminRepo.findById(admin.getAdminId());
//		return opt.get();
//	}


	@Override
	public ResponseEntity<AdminDTO>getAdmin(int id) {
			Admin admin=adminRepo.findById(id).get();
			AdminDTO dto=modelMapper.map(admin, AdminDTO.class);
		return new ResponseEntity<AdminDTO>(dto,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<AdminDTO>> getAdminList() {
		List<Admin> list= adminRepo.findAll();
		List<AdminDTO> dtoList=list.stream().map(a->modelMapper.map(a, AdminDTO.class)).toList();
		return new ResponseEntity<List<AdminDTO>>(dtoList,HttpStatus.OK);
	}

}
