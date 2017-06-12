package com.spring.tx.xml;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.tx.xml.service.BookShopService;
import com.spring.tx.xml.service.Cashier;

public class SpringTransactionTest {

	private ApplicationContext applicationContext = null;
	private BookShopDao bsd;
	private BookShopService bss;
	private Cashier cashier;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext-tx-xml.xml");
		bsd = applicationContext.getBean(BookShopDao.class);
		bss = applicationContext.getBean(BookShopService.class);
		cashier = applicationContext.getBean(Cashier.class);
	}
	
	@Test
	public void testcheckout() {
		cashier.checkout("zhang", Arrays.asList(1001,1002));
	}
	
	@Test
	public void testpurchase() {
		bss.purchase("zhang", 1001);
	}

}
