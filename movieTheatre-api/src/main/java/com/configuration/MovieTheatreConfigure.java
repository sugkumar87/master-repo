package com.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


// @WebListener
// public class MovieTheatreConfigure  implements ServletContextListener {

public class MovieTheatreConfigure  {

	public void contextInitialized(ServletContextEvent sce) {
	
		System.out.println("Called from servlet context class");
		//sce.getServletContext().getSessionCookieConfig().setSecure(true);
	}
	
}
