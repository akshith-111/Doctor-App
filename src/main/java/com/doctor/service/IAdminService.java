package com.doctor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doctor.dto.AdminDTO;
import com.doctor.entity.Admin;
import com.doctor.model.AdminModel;

@Service
public interface IAdminService {

	public AdminDTO saveAdmin(AdminModel adminModel);
	public Admin udpateAdmin(Admin admin);
	public Admin removeAdmin(Admin admin);
//	public Admin viewAdmin(Admin admin);
	ResponseEntity<AdminDTO> getAdmin(int id);
	public ResponseEntity<List<AdminDTO>> getAdminList();
	
	
}
