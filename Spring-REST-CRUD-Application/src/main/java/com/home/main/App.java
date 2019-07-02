package com.home.main;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.home.configuration.SpringConfig;
import com.home.model.Book;
import com.home.utility.AppConfig;
import com.home.utility.LogConfig;


public class App 
{
	final static Logger LOGGER = LogConfig.getLogger(App.class);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		System.out.println("Program Execution Started");
		System.out.println("***************************************************");
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println("Application Context Created and Loaded");
		LOGGER.info("Context creation done");
		System.out.println("Created application context");
		Book book = applicationContext.getBean(Book.class);
		System.out.println("Got Bean of type Book and bean object is - "+book);
		LOGGER.info("Bean retrieval done");
		System.out.println("***************************************************");
		System.out.println("Now it`s time to access values from properties file");
		System.out.println("***************************************************");
		System.out.println(AppConfig.PROPERTIES.getProperty("Database.USERNAME"));
		System.out.println(AppConfig.PROPERTIES.getProperty("LoginApi.LOGIN_FAILURE"));
		LOGGER.info("Values from properties file accessed");
		System.out.println("***************************************************");
		System.out.println("Program Execution Completed");
	}
}
