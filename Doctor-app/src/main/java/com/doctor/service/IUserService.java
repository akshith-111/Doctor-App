package com.doctor.service;

import org.springframework.stereotype.Service;

import com.doctor.entity.User;

@Service
public interface IUserService{

	public User validateUser(User user);
	public User addUser(User user);
	public User removeUser(User user);
	public User updateUser(User user);
}
