package com.spring.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//�����������Ϊһ�����棺��Ҫ�Ѹ�����뵽IOC �����С�������Ϊһ������
@Aspect
@Component
public class LoggingAspect {

	//�����÷�����һ��ǰ��֪ͨ����Ŀ�귽����ʼ֮ǰִ��
	@Before("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method "+ methodName +" begins with"+args);
	}
	
	//����֪ͨ����Ŀ�귽��ִ�к������Ƿ����쳣����ִ�е�֪ͨ
	//�ں���֪ͨ�л����ܷ���Ŀ�귽��ִ�еĽ��
	@After("execution(* com.spring.aop.impl.*.*(int, int))")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method "+ methodName +" end");
	}
}
