package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class MovieAPIResponseFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
			HttpServletResponse httpresp = (HttpServletResponse) resp; 
		
			httpresp.addHeader("X-FRAME-OPTIONS", "DENY");
			
			chain.doFilter(req, resp);
		
	}

	
	
}
