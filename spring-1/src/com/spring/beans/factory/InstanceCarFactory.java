package com.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * ʵ������������ʵ�������ķ�����������Ҫ�������������ٵ��ù�����ʵ������������bean�� ʵ��
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
