package com.spring.hibernate.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.hibernate.service.BookShopService;
import com.spring.hibernate.service.Cashier;
 
public class SpringHibernate {
	
	private ApplicationContext acc = null;
	private BookShopService bookShopService = null;
	private Cashier cashier;
	
	{
		acc = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopService = acc.getBean(BookShopService.class);
		cashier = acc.getBean(Cashier.class);
	}

	@Test
	public void testCashier(){
		cashier.checkout("aa", Arrays.asList(1001,1002));
	}
	
	@Test
	public void testBookShopService(){
		bookShopService.purchase("aa", 1001);
	}
	
	@Test
	public void testDatasource() throws SQLException {
		DataSource ds = acc.getBean(DataSource.class);
		System.out.println(ds.getConnection());
	}

}
