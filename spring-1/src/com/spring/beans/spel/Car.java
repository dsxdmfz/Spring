package com.spring.beans.spel;

public class Car {
	private String name;
	private  double price;
	private double tyrePerimeter;
	
	public double getTyrePerimeter() {
		return tyrePerimeter;
	}
	public void setTyrePerimeter(double tyrePerimeter) {
		this.tyrePerimeter = tyrePerimeter;
	}
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
		return "Car [name=" + name + ", price=" + price + ", tyrePerimeter="
				+ tyrePerimeter + "]";
	}
	public Car() {
		// TODO Auto-generated constructor stub
		System.out.println("Car¡¯s Constructor...");
	}

}
