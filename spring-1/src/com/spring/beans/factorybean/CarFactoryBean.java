package com.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car>{
	private String brand;
	public void setBrand(String brand) {
		this.brand = brand;
	}

	//返回bean的对象
	@Override
	public Car getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Car(brand, 500000);
	}
	//返回bean 的类型
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}
	//是否单例
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
