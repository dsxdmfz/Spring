package com.spring.aop.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		//1、创建Spring 的IOC 容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2、从IOC 容器中获取Bean 的实例
		ArithmeticCalculator arithmeticCalculator = ctx.getBean(ArithmeticCalculator.class);
		//3、使用bean
		int result = arithmeticCalculator.add(3, 6);
		System.out.println("result:"+result);
		
		result = arithmeticCalculator.sub(6, 2);
		System.out.println("result:"+result);
		
		result = arithmeticCalculator.mul(1, 5);
		System.out.println("result:"+result);
		
		result = arithmeticCalculator.div(12, 2);
		System.out.println("result:"+result);
	}
}
