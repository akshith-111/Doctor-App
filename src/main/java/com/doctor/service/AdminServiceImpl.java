package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.dto.AdminDTO;
import com.doctor.entity.Admin;
import com.doctor.entity.User;
import com.doctor.exceptionhandling.ResourceNotFoundException;
import com.doctor.model.AdminModel;
import com.doctor.repo.AdminRepo;
import com.doctor.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {


    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final AdminRepo adminRepo;
	
	private final UserRepo userRepo;

    
	@Override
	public AdminDTO saveAdmin(Admin admin) {
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		User user=new User();
		admin=adminRepo.save(admin);
		user.setPassword(admin.getPassword());
		user.setRole("ADMIN");
		user.setUsername(admin.getEmail());
		userRepo.save(user);//saving user object
		AdminDTO adminDTO=modelMapper.map(admin, AdminDTO.class);
		return adminDTO;
	}

//
//	@Override
//	public AdminDTO udpateAdmin(AdminModel adminModel) {
//		
//		return adminRepo.save(admin);
//	}


	@Override
	public Admin removeAdmin(Admin admin) {
		Optional<Admin> opt= adminRepo.findById(admin.getAdminId());
		opt.orElseThrow(()->new ResourceNotFoundException("No Data Found on this id: "+admin.getAdminId()));
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
			Admin admin=adminRepo.findById(id)
					.orElseThrow(()->new ResourceNotFoundException("No data Found on this id "+id));
			AdminDTO dto=modelMapper.map(admin, AdminDTO.class);
		return new ResponseEntity<AdminDTO>(dto,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<AdminDTO>> getAdminList() {
		List<Admin> list= adminRepo.findAll();
		List<AdminDTO> dtoList=list.stream().map(a->modelMapper.map(a, AdminDTO.class)).toList();
		return new ResponseEntity<List<AdminDTO>>(dtoList,HttpStatus.OK);
	}

	@Override
	public Admin udpateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
