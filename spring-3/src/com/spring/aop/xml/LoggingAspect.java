package com.spring.aop.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	
	public void beforMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins" + Arrays.asList(args));
	}
	
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends");
	}
	public void afterReturningMethod(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with "+result);
	}
	
	public void afterThrowing(JoinPoint joinPoint,Exception e){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method "+methodName+" occurs with "+e);
	}
	
	public Object aroundMethod(ProceedingJoinPoint pj){
		Object result = null;
		String methodName = pj.getSignature().getName();
		try {
			//前置通知
			System.out.println("around-->The method "+methodName+" begins with"+Arrays.asList(pj.getArgs()));
			result = pj.proceed();
			//返回通知
			System.out.println("around-->The method "+methodName+" ends with "+result);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常通知
			System.out.println("around-->The method "+methodName+" occurs with"+e);
		}
		//后置通知
		System.out.println("around-->The method "+methodName+" ends");
		return result;
	}
	
}
