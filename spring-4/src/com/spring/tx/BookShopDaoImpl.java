package com.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int findBookPriceByBookNumber(int b_num) {
		// TODO Auto-generated method stub
		String sql = "select price from book where b_num = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, b_num);
	}

	@Override
	public void updateBookStock(int b_num) {
		// TODO Auto-generated method stub
		//�����Ŀ���Ƿ��㹻�������㣬���׳��쳣
		String sql2 = "select stock from book_stock where b_name = (select b_name from book where b_num = ?)";
		int stock = jdbcTemplate.queryForObject(sql2, Integer.class, b_num);
		if (stock == 0) {
			throw new BookStockException("��治�㣡");
		}
		String sql = "update book_stock set stock = (stock -1) where b_name = (select b_name from book where b_num = ?)";
		jdbcTemplate.update(sql, b_num);
	}

	@Override
	public void updateUserAccount(String username, int price) {
		// TODO Auto-generated method stub
		//����˻�����Ƿ��㹻�������㣬���׳��쳣
		String sql2 = "select balance from account where username = ?";
		int balance = jdbcTemplate.queryForObject(sql2, Integer.class, username);
		if(balance < price){
			throw new UserAccountException("���㣡");
		}
		String sql = "update account set balance = (balance - ?) where username = ?";
		jdbcTemplate.update(sql, price,username);
	}

}
