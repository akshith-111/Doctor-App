package com.doctor.service;

import org.springframework.stereotype.Service;

import com.doctor.entity.User;

@Service
public interface IUserService{

	public User saveUser(User user);
}
