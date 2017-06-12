package com.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;
	
	//添加事务注解
	//1、使用propagation 指定事务的传播行为，即当前的事务方法被另一个事务方法调用时
	//如何使用事务，默认取值为REQUIRES，即使用调用方法的事务
	//REQUIRES_NEW：事务自己的事务，调用的事务方法的事务被挂起
	//2、使用isolation 指定事务的隔离级别，最常用的取值为READ_COMMITTED
	//3、默认情况下spring 的声明式事务对所有的运行时异常进行回滚，也可以通过对应的
	//属性进行设置，通常情况下默认值即可。
	//4、使用readOnly 指定事务是否只读。表示这个事务只读数据但不更新数据，
	//这样可以帮助数据库引擎优化事务，若真的是一个只读数据库值得方法，应设置readOnly = true
	//5、使用timeout 指定强制回滚之前事务可以占用的时间
//	@Transactional(propagation=Propagation.REQUIRES_NEW,
//					isolation = Isolation.READ_COMMITTED,
//					noRollbackFor = {UserAccountException.class})
	@Transactional(propagation=Propagation.REQUIRES_NEW,
			isolation = Isolation.READ_COMMITTED,
			readOnly = false,
			timeout = 3)
	@Override
	public void purchase(String username, int b_num) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
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
