package com.spring.beans.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.beans.annotation.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	
	public void setUserRepository(@Qualifier("userJdbcRepository")UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void  add() {
		System.out.println("UserService add...");
		userRepository.save();
	}
}
