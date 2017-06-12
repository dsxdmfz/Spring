package com.spring.beans.collectic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person person = (Person) act.getBean("person3");
		System.out.println(person);
		
		NewPerson newPerson = (NewPerson) act.getBean("newPerson");
		System.out.println(newPerson);
		
		DataSource dataSource = (DataSource) act.getBean("properties");
		System.out.println(dataSource);
		
		Person person4 = (Person) act.getBean("person4");
		System.out.println(person4);
	}

}
