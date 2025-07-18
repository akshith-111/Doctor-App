package com.doctor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.dto.AdminDTO;
import com.doctor.service.IAdminService;
import com.doctor.service.IAppointmentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	
	private final IAppointmentService appointmentService;

	private final IAdminService adminService;


	//ADMIN OPERATIONS
	
	@GetMapping("/getadmin/{id}")
	public ResponseEntity<AdminDTO> getadmin(@PathVariable int id) {
		return adminService.getAdmin(id);
	}
	@GetMapping("/getadmins")
	public ResponseEntity<List<AdminDTO>> getadmins() {
		return adminService.getAdminList();
	}
	
	
	//status
	@GetMapping("status/{id}")
	public ResponseEntity<String> getAppointmentStatus(@PathVariable int id) {
		String status=appointmentService.getAppointment(id).getStatus();
		return ResponseEntity.ok(status);
	}
	

}
