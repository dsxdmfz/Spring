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
		//1����ȡ��ĵ���
		int price = bookShopDao.findBookPriceByBookNumber(b_num);
		//2��������Ŀ��
		bookShopDao.updateBookStock(b_num);
		//3�������˻������
		bookShopDao.updateUserAccount(username, price);
	}

}
