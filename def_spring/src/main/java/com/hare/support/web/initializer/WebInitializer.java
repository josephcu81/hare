package com.hare.support.web.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.hare.support.web.config.AppConfig;
import com.hare.support.web.config.DispatcherConfig;


public class WebInitializer implements WebApplicationInitializer  {
	
	private final String CONFIG_LOCATION = "com.hare.support.web.config"; 
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// dispatcher servlet 구현
		registerDispatcherServlet(servletContext);
		// filter 구현
		registerCharacterEncodingFilter(servletContext);
		
	}
	
	/**
	 * encoding filter 설정
	 * @param servletContext
	 */
	private void registerCharacterEncodingFilter(ServletContext servletContext) {
		
		
	}
	
	/**
	 * dispatcher Servlet 설정
	 * @param servletContext
	 */
	private void registerDispatcherServlet(ServletContext container) {
		// spring javadoc api 기준
		// 1. create the root String application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		container.addListener(new ContextLoaderListener(rootContext));		
		// 2create dispather servlet
		AnnotationConfigWebApplicationContext dispacherContext = new AnnotationConfigWebApplicationContext();
		dispacherContext.register(DispatcherConfig.class);
		
		//3. mapping servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispacherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		//context.setConfigLocation(CONFIG_LOCATION);
	}

}
