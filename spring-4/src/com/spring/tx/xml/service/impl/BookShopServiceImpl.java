package com.spring.tx.xml.service.impl;

import com.spring.tx.xml.BookShopDao;
import com.spring.tx.xml.service.BookShopService;


public class BookShopServiceImpl implements BookShopService {

	private BookShopDao bookShopDao;
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	@Override
	public void purchase(String username, int b_num) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//1、获取书的单价
		int price = bookShopDao.findBookPriceByBookNumber(b_num);
		//2、更新书的库存
		bookShopDao.updateBookStock(b_num);
		//3、更新账户的余额
		bookShopDao.updateUserAccount(username, price);
	}

}
