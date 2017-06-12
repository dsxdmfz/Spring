package com.spring.hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hibernate.service.BookShopService;
import com.spring.hibernate.service.Cashier;

@Service
public class CashierImpl implements Cashier {

	@Autowired
	private BookShopService bookShopService;
	
	@Override
	public void checkout(String username, List<Integer> b_nums) {
		// TODO Auto-generated method stub
		for (Integer b_num : b_nums) {
			bookShopService.purchase(username, b_num);
		}
	}

}
