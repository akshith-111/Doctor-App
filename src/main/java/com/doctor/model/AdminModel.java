package com.doctor.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel {

	private String adminName;
	private String contactNumber;
	private String email;
	private String password;
}
