package com.spring.aop.helloworld;

public class Main {

	public static void main(String[] args) {
//		ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorLoggingImpl();
		
		ArithmeticCalculator target = new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getTarget();
		
		int result1 = proxy.add(1, 0);
		System.out.println("-->"+result1);
		
		int result2 = proxy.sub(4, 2);
		System.out.println("-->"+result2);
		
		int result3 = proxy.mul(3, 1);
		System.out.println("-->"+result3);
		
		int result4 = proxy.div(8, 2);
		System.out.println("-->"+result4);
	}
}
