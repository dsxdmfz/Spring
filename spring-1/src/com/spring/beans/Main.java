package com.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		/*
		//创建HelloWorld的一个对象
		HelloWorld helloWorld = new HelloWorld();
		//为name属性赋值
		helloWorld.setName("world");
		*/
		
		//1.创建Spring的IOC容器对象
		//ApplicationContext 代表IOC容器
		//ClassPathXmlApplicationContext  是ApplicationContext接口的实现类。该实现类从类路径下来加载配置文件。
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从IOC容器中获取Bean实例
		//利用id定位到IOC容器中的Bean
		HelloWorld helloWorld = (HelloWorld) act.getBean("helloworld");
		//利用类型返回IOC容器中的Bean，但要求容器中必须只有一个该类型的Bean。
//		HelloWorld helloWorld2 = act.getBean(HelloWorld.class);
		//3.调用hello方法
//		helloWorld.hello();
		Car car = (Car) act.getBean("car");
		System.out.println(car);
		Car car2 = (Car) act.getBean("car2");
		System.out.println(car2);
		
		Person person = (Person) act.getBean("person2");
		System.out.println(person);
	}

}
