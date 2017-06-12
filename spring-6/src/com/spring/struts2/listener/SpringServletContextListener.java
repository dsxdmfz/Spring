package com.spring.struts2.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application Lifecycle Listener implementation class SpringServletContextListener
 *
 */
@WebListener
public class SpringServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SpringServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//1、获取Spring 配置文件的名称
		ServletContext servletContext = arg0.getServletContext();
		String config = servletContext.getInitParameter("configLation");
		//1、创建IOC容器
		ApplicationContext acc = new ClassPathXmlApplicationContext(config);
		//2、把IOC容器放在ServletContext 的一个属性中
		servletContext.setAttribute("ApplicationContext", acc);
	}
	
}
