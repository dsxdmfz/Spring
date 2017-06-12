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
	
	//�������ע��
	//1��ʹ��propagation ָ������Ĵ�����Ϊ������ǰ�����񷽷�����һ�����񷽷�����ʱ
	//���ʹ������Ĭ��ȡֵΪREQUIRES����ʹ�õ��÷���������
	//REQUIRES_NEW�������Լ������񣬵��õ����񷽷������񱻹���
	//2��ʹ��isolation ָ������ĸ��뼶����õ�ȡֵΪREAD_COMMITTED
	//3��Ĭ�������spring ������ʽ��������е�����ʱ�쳣���лع���Ҳ����ͨ����Ӧ��
	//���Խ������ã�ͨ�������Ĭ��ֵ���ɡ�
	//4��ʹ��readOnly ָ�������Ƿ�ֻ������ʾ�������ֻ�����ݵ����������ݣ�
	//�������԰������ݿ������Ż������������һ��ֻ�����ݿ�ֵ�÷�����Ӧ����readOnly = true
	//5��ʹ��timeout ָ��ǿ�ƻع�֮ǰ�������ռ�õ�ʱ��
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
		//1����ȡ��ĵ���
		int price = bookShopDao.findBookPriceByBookNumber(b_num);
		//2��������Ŀ��
		bookShopDao.updateBookStock(b_num);
		//3�������˻������
		bookShopDao.updateUserAccount(username, price);
	}

}
