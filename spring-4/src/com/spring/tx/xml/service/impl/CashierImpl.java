package com.spring.tx.xml.service.impl;

import java.util.List;

import com.spring.tx.xml.service.BookShopService;
import com.spring.tx.xml.service.Cashier;

public class CashierImpl implements Cashier {

	private BookShopService bookShopService;
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	@Override
	public void checkout(String username, List<Integer> b_nums) {
		// TODO Auto-generated method stub
		for (Integer b_num : b_nums) {
			bookShopService.purchase(username, b_num);
		}
	}
}
