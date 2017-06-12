package com.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationCantext.xml");
		
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");
		
		System.out.println(arithmeticCalculator.getClass().getName());
		System.out.println();
		
		int result = arithmeticCalculator.add(1, 2);
		System.out.println("result:"+result);
		
		result = arithmeticCalculator.div(100, 10);
		System.out.println("result:"+result);
		
		result = arithmeticCalculator.sub(10, 5);
		System.out.println("result:"+result);
	}
}
