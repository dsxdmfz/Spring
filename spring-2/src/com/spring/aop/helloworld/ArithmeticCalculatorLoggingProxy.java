package com.spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {

	//Ҫ����Ķ���
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	public ArithmeticCalculator getTarget() {
		ArithmeticCalculator proxy = null;
		//�����������һ����������������
		ClassLoader classloader = target.getClass().getClassLoader();
		//�����������ͣ�����������Щ����
		Class[] aclass = new Class[]{ArithmeticCalculator.class};
		//���ô���������еķ���ʱ����ִ�еĴ���
		InvocationHandler invocationhandler = new InvocationHandler() {
			
			/* 
			 * Object:���ڷ��ص��Ǹ��������һ������£���invoke�����ж���ʹ�øĶ���
			 * Method�����ڱ����õķ���
			 * Object[]�����÷���ʱ������Ĳ���
			 */
			@Override
			public Object invoke(Object obj, Method method, Object[] aobj)
					throws Throwable {
				// TODO Auto-generated method stub
				String methodName = method.getName();
				//��ӡ��־
				System.out.println("The method"+methodName+"begins with"+Arrays.asList(aobj));
				//����Ŀ��ķ���
				Object result = null;
				try {
					//ǰ��֪ͨ
					result = method.invoke(target, aobj);
					//����֪ͨ�����Է��ʵ������ķ���ֵ
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//�쳣֪ͨ�����Է��ʵ��������ֵ��쳣
				}
				//����֪ͨ����Ϊ�������ܻ���쳣�����Է��ʲ��������ķ���ֵ
				
				//��ӡ��־
				System.out.println("The method"+methodName+"end with"+result);
				return result;
			}
		};
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(classloader, aclass, invocationhandler);
		return proxy;
	}
}
