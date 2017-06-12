package com.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		/*
		//����HelloWorld��һ������
		HelloWorld helloWorld = new HelloWorld();
		//Ϊname���Ը�ֵ
		helloWorld.setName("world");
		*/
		
		//1.����Spring��IOC��������
		//ApplicationContext ����IOC����
		//ClassPathXmlApplicationContext  ��ApplicationContext�ӿڵ�ʵ���ࡣ��ʵ�������·���������������ļ���
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.��IOC�����л�ȡBeanʵ��
		//����id��λ��IOC�����е�Bean
		HelloWorld helloWorld = (HelloWorld) act.getBean("helloworld");
		//�������ͷ���IOC�����е�Bean����Ҫ�������б���ֻ��һ�������͵�Bean��
//		HelloWorld helloWorld2 = act.getBean(HelloWorld.class);
		//3.����hello����
//		helloWorld.hello();
		Car car = (Car) act.getBean("car");
		System.out.println(car);
		Car car2 = (Car) act.getBean("car2");
		System.out.println(car2);
		
		Person person = (Person) act.getBean("person2");
		System.out.println(person);
	}

}
