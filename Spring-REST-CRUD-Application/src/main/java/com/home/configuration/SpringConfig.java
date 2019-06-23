package com.home.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.home.model.Book;

@Configuration
public class SpringConfig 
{
	@Bean
	public Book book()
	{
		return new Book();
	}
}
