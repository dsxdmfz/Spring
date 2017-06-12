package com.spring.hibernate.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.hibernate.dao.BookShopDao;
import com.spring.hibernate.exception.BookStockException;
import com.spring.hibernate.exception.UserAccountException;

@Repository
public class BookShopDaoImpl implements BookShopDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//不推荐使用 HibernateTemplate 和 HibernateDaoSupport
	//因为这样会导致 Dao 和 Spring 的 API 进行耦合
	//可以移植性变差
//	private HibernateTemplate hibernateTemplate;
	
	//获取和当前线程绑定的 Session. 
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}


	@Override
	public int findBookPriceByBookNumber(int isbn) {
		// TODO Auto-generated method stub
		String hql = "SELECT price FROM Book WHERE isbn = ?";
		Query query = getSession().createQuery(hql).setInteger(0, isbn);
		return (Integer)query.uniqueResult();
	}
	
	@Override
	public void updateUserAccount(String name, int price) {
		//验证余额是否足够
		String hql2 = "SELECT balance FROM Account WHERE name = ?";
		int balance = (int) getSession().createQuery(hql2).setString(0, name).uniqueResult();
		if(balance < price){
			throw new UserAccountException("余额不足!");
		}
		String hql = "UPDATE Account SET balance = balance - ? WHERE name = ?";
		getSession().createQuery(hql).setInteger(0, price).setString(1, name).executeUpdate();
	}

	@Override
	public void updateBookStock(int isbn) {
		// TODO Auto-generated method stub
		//验证书的库存是否充足. 
		String hql2 = "SELECT stock FROM Book WHERE isbn = ?";
		int stock = (int) getSession().createQuery(hql2).setInteger(0, isbn).uniqueResult();
		if(stock == 0){
			throw new BookStockException("库存不足!");
		}				
		String hql = "UPDATE Book SET stock = stock - 1 WHERE isbn = ?";
		getSession().createQuery(hql).setInteger(0, isbn).executeUpdate();
	}

}
