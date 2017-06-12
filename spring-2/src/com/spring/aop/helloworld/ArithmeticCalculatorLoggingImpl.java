package com.spring.aop.helloworld;

public class ArithmeticCalculatorLoggingImpl extends ArithmeticException implements
		ArithmeticCalculator {

	@Override
	public int add(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("The methed add begins with ["+i+","+j+"]");
		int result = i+j;
		System.out.println("The methed add end with + result");
		return result;
	}

	@Override
	public int sub(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("The methed sub begins with ["+i+","+j+"]");
		int result = i-j;
		System.out.println("The methed sub end with + result");
		return result;
	}

	@Override
	public int mul(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("The methed mul begins with ["+i+","+j+"]");
		int result = i*j;
		System.out.println("The methed mul end with + result");
		return result;
	}

	@Override
	public int div(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("The methed div begins with ["+i+","+j+"]");
		int result = i/j;
		System.out.println("The methed div end with + result");
		return result;
	}

}
