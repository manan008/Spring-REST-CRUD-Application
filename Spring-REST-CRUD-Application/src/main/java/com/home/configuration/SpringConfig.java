package com.home.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.home.model.Book;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.home.service com.home.dao com.home.utiltiy")
public class SpringConfig 
{
	@Bean
	public Book book()
	{
		System.out.println("Book type bean of name book created");
		return new Book();
	}
}
