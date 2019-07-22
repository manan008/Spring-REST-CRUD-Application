package com.home.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.home.configuration.SpringConfig;

public class ContextFactory {

	private static final ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(SpringConfig.class);
	
	public static ApplicationContext getContext()
	{
		return CONTEXT;
	}
}
