package com.spring.tx;

public interface BookShopDao {

	//根据书号获取书的单价
	public int findBookPriceByBookNumber(int b_num);
	
	//更新书的库存。使书号对应的库存-1
	public void updateBookStock(int b_num);
	
	//更新用户的账户余额。使username 的balance - price
	public void updateUserAccount(String username, int price);
}
