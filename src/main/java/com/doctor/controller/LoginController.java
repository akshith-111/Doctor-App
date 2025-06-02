package com.doctor.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.jwtutil.JwtUtil;
import com.doctor.model.AuthModel;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class LoginController {

	private final AuthenticationManager authenticationManager;
	
	private final JwtUtil jwtUtil;
		
	@PostMapping("/login")
	public String login(@RequestBody AuthModel authModel) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getUsername(),authModel.getPassword()));
				
		return jwtUtil.generateToken(authModel.getUsername());
	}
	
	
	
}
