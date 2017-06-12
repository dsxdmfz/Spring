package com.spring.beans.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.beans.annotation.controller.UserController;
import com.spring.beans.annotation.repository.UserRepository;
import com.spring.beans.annotation.service.UserService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
//		TestObject testObject = (TestObject) ctx.getBean("testObject");
//		System.out.println(testObject);
//		
		UserController userController = (UserController) ctx.getBean("userController");
		System.out.println(userController);
		userController.execute();
//	
//		UserService us = (UserService) ctx.getBean("userService");
//		System.out.println(us);
		
//		UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
//		System.out.println(userRepository);
	
	}
}
