package com.spring.beans.spel;

public class Person {
	private String name;
	private Address address;
	private Car car;
	
	//应用address bean的city 属性
	private String city;
	
	//根据car 的price 确定info ：car的price>=300000 :金领 否则为白领
	private String info;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", car=" + car
				+ ", city=" + city + ", info=" + info + "]";
	}

}
