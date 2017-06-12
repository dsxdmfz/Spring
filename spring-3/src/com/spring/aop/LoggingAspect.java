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

//可以使用@Order 注解指定切面的优先级，值越小优先级越高
@Order(2)
@Aspect
@Component
public class LoggingAspect {
	
	/**
	 *定义一个方法，用于声明切入点表达式。一般的，该方法中再不需要添加其它代码 
	 *使用@Pointcut 来声明切入点表达式
	 *后面的其他通知直接使用方法名来引用当前的切入点表达式
	 */
	@Pointcut("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(..))")
	public void declearJoinPointExperssion(){}

	//在com.spring.aop.ArithmeticCalculator 接口的每一个实现类的每一个方法开始之前执行一段代码
//	@Before("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(int, int))")
	@Before("declearJoinPointExperssion()")
	public void beforMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();
		System.out.println("The method " + methodName + " begins" + Arrays.asList(args));
	}
	
	/**
	 * 在方法执行之后执行的代码，无论改方法是否出现异常
	 */
//	@After("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(int, int))")
	@After("declearJoinPointExperssion()")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends");
	}
	
	/**
	 * 在方法正常结束时执行的代码
	 * 返回通知是可以访问到方法的返回值！
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
	 * 在目标方法出现异常时执行的代码
	 * 可以访问到异常对象；且可以指定出现特定异常时再执行通知代码
	 * @param joinPoint
	 * @param e 可以指定出现特定异常
	 */
//	@AfterThrowing(value="execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(int, int))",
//			throwing="e")
	@AfterThrowing(value="declearJoinPointExperssion()",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,Exception e){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method "+methodName+" occurs with "+e);
	}
	
	/**
	 * 环绕通知需要携带ProceedingJoinPoint 类型的参数
	 * 环绕通知类似于动态代理的全过程:ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
	 * 且环绕通知必须有返回值，返回值即为目标方法的返回值
	 * @param pj
	 */
//	@Around("execution(public * com.spring.aop.ArithmeticCalculatorImpl.*(..))")
	@Around("declearJoinPointExperssion()")
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
