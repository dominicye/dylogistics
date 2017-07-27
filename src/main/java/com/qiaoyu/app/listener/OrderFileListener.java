package com.qiaoyu.app.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiaoyu.app.base.BaseThread;

/**
 * Application Lifecycle Listener implementation class OrderFileListener
 *
 */
public class OrderFileListener implements ServletContextListener {
	
	 private static ApplicationContext applicationContext = null; 
	
    /**
     * Default constructor. 
     */
    public OrderFileListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	 System.out.println("servlet context destory.......");
    	 BaseThread bt = (BaseThread) applicationContext.getBean("baseThread");
    	 bt.setFlag(false);
         applicationContext = null;
         System.out.println("resource released .......");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
    	System.out.println("servlet context init.....");
    	BaseThread bt = (BaseThread) applicationContext.getBean("baseThread");
		Thread t = new Thread(bt);
		t.start();
    }

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
