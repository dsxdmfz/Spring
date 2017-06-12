package com.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：实例工厂的方法。即先需要创建工厂本身，再调用工厂的实例方法来返回bean的 实例
 * @author zhangl
 *
 */
public class InstanceCarFactory {
	private Map<String, Car> cars = null;
	
	public InstanceCarFactory() {
		// TODO Auto-generated constructor stub
		cars = new HashMap<String, Car>();
		cars.put("Audi", new Car("Audi",310000));
		cars.put("Ford", new Car("Fors", 410000));
	}
	
	public Car getCar(String name){
		return cars.get(name);
	}
}
