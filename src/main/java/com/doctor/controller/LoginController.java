package com.doctor.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
		Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getUsername(),authModel.getPassword()));
		UserDetails userDetails=(UserDetails)auth.getPrincipal();
		return jwtUtil.generateToken(userDetails);
	}
	
	
	
}
