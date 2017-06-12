package com.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//����ʹ��@Order ע��ָ����������ȼ���ֵԽС���ȼ�Խ��
@Order(2)
@Aspect
@Component
public class LoggingAspect {
	
	/**
	 *����һ�����������������������ʽ��һ��ģ��÷������ٲ���Ҫ����������� 
	 *ʹ��@Pointcut �������������ʽ
	 *���������ֱ֪ͨ��ʹ�÷����������õ�ǰ���������ʽ
	 */
	@Pointcut("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(..))")
	public void declearJoinPointExperssion(){}

	//��com.spring.aop.ArithmeticCalculator �ӿڵ�ÿһ��ʵ�����ÿһ��������ʼ֮ǰִ��һ�δ���
//	@Before("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(int, int))")
	@Before("declearJoinPointExperssion()")
	public void beforMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins" + Arrays.asList(args));
	}
	
	/**
	 * �ڷ���ִ��֮��ִ�еĴ��룬���۸ķ����Ƿ�����쳣
	 */
//	@After("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(int, int))")
	@After("declearJoinPointExperssion()")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends");
	}
	
	/**
	 * �ڷ�����������ʱִ�еĴ���
	 * ����֪ͨ�ǿ��Է��ʵ������ķ���ֵ��
	 * @param joinPoint
	 * @param result
	 */
//	@AfterReturning(value="execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(int, int))",
//			returning="result")
	@AfterReturning(value="declearJoinPointExperssion()",returning="result")
	public void afterReturningMethod(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with "+result);
	}
	
	/**
	 * ��Ŀ�귽�������쳣ʱִ�еĴ���
	 * ���Է��ʵ��쳣�����ҿ���ָ�������ض��쳣ʱ��ִ��֪ͨ����
	 * @param joinPoint
	 * @param e ����ָ�������ض��쳣
	 */
//	@AfterThrowing(value="execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(int, int))",
//			throwing="e")
	@AfterThrowing(value="declearJoinPointExperssion()",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,Exception e){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method "+methodName+" occurs with "+e);
	}
	
	/**
	 * ����֪ͨ��ҪЯ��ProceedingJoinPoint ���͵Ĳ���
	 * ����֪ͨ�����ڶ�̬�����ȫ����:ProceedingJoinPoint ���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽��
	 * �һ���֪ͨ�����з���ֵ������ֵ��ΪĿ�귽���ķ���ֵ
	 * @param pj
	 */
//	@Around("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(..))")
	@Around("declearJoinPointExperssion()")
	public Object aroundMethod(ProceedingJoinPoint pj){
		Object result = null;
		String methodName = pj.getSignature().getName();
		try {
			//ǰ��֪ͨ
			System.out.println("around-->The method "+methodName+" begins with"+Arrays.asList(pj.getArgs()));
			result = pj.proceed();
			//����֪ͨ
			System.out.println("around-->The method "+methodName+" ends with "+result);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�쳣֪ͨ
			System.out.println("around-->The method "+methodName+" occurs with"+e);
		}
		//����֪ͨ
		System.out.println("around-->The method "+methodName+" ends");
		return result;
	}
	
}
