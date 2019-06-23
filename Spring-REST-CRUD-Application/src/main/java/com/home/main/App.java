package com.home.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.home.configuration.SpringConfig;
import com.home.model.Book;

public class App {
	public static void main(String[] args) {
		System.out.println("Entered main");
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println("Created application context");
		Book book = applicationContext.getBean(Book.class);
		System.out.println("Got Bean");
		System.out.println(book);
	}
}
