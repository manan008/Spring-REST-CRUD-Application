package com.home.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.home.model.Book;

@Configuration
@ComponentScan(basePackages = "com.home.service com.home.dao")
public class SpringConfig 
{
	@Bean
	public Book book()
	{
		return new Book();
	}
}
