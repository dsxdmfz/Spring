package com.spring.beans.autowire;

public class Car {
	private String name;
	private  double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [name=" + name + ", price=" + price + "]";
	}
	public Car() {
		// TODO Auto-generated constructor stub
		System.out.println("Car¡¯s Constructor...");
	}

}
