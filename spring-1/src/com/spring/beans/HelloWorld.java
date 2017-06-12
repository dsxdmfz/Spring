package com.spring.beans;

public class HelloWorld {
	private String name;
	
	public HelloWorld() {
		// TODO Auto-generated constructor stub
		System.out.println("HelloWorld's Constructor...");
	}
	
	public void setName(String name){
		System.out.println("setName:"+name);
		this.name = name;
	}
	
	public void hello(){
		System.out.println("hello:"+name);
	}

}
