package com.doctor.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.doctor.entity.Admin;

@Service
public interface IAdminService {

	public Admin saveAdmin(Admin admin);
	public Admin udpateAdmin(Admin admin);
	public Admin removeAdmin(Admin admin);
	public Admin viewAdmin(Admin admin);
	
	
}
