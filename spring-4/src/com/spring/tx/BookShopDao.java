package com.spring.tx;

public interface BookShopDao {

	//������Ż�ȡ��ĵ���
	public int findBookPriceByBookNumber(int b_num);
	
	//������Ŀ�档ʹ��Ŷ�Ӧ�Ŀ��-1
	public void updateBookStock(int b_num);
	
	//�����û����˻���ʹusername ��balance - price
	public void updateUserAccount(String username, int price);
}
