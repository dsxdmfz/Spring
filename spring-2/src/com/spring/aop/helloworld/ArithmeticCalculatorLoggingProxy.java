package com.spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {

	//要代理的对象
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	public ArithmeticCalculator getTarget() {
		ArithmeticCalculator proxy = null;
		//代理对象由哪一个类加载器负责加载
		ClassLoader classloader = target.getClass().getClassLoader();
		//代理对象的类型，即其中有哪些方法
		Class[] aclass = new Class[]{ArithmeticCalculator.class};
		//调用代理对象其中的方法时，该执行的代码
		InvocationHandler invocationhandler = new InvocationHandler() {
			
			/* 
			 * Object:正在返回的那个代理对象，一般情况下，在invoke方法中都不使用改对象
			 * Method：正在被调用的方法
			 * Object[]：调用方法时，传入的参数
			 */
			@Override
			public Object invoke(Object obj, Method method, Object[] aobj)
					throws Throwable {
				// TODO Auto-generated method stub
				String methodName = method.getName();
				//打印日志
				System.out.println("The method"+methodName+"begins with"+Arrays.asList(aobj));
				//调用目标的方法
				Object result = null;
				try {
					//前置通知
					result = method.invoke(target, aobj);
					//返回通知，可以访问到方法的返回值
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//异常通知，可以访问到方法出现的异常
				}
				//后置通知，因为方法可能会出异常，所以访问不到方法的返回值
				
				//打印日志
				System.out.println("The method"+methodName+"end with"+result);
				return result;
			}
		};
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(classloader, aclass, invocationhandler);
		return proxy;
	}
}
