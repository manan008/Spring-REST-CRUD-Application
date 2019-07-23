package com.home.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@PropertySources({
	@PropertySource("classpath:/com/home/resources/database.properties"),
	@PropertySource("classpath:/com/home/resources/configuration.properties")
})
@ComponentScan(basePackages = "com.home.service com.home.dao com.home.utiltiy")
public class SpringConfig 
{
	/*
	 * @Bean public Book book() {
	 * System.out.println("Book type bean of name book created"); return new Book();
	 * }
	 */
	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("Database.DRIVER_CLASS_NAME"));
		dataSource.setUrl(environment.getProperty("Database.URL"));
		dataSource.setUsername(environment.getProperty("Database.USERNAME"));
		dataSource.setPassword(environment.getProperty("Database.PASSWORD"));
		return dataSource;
	}
	
	@Bean("sessionFactory")
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("com.home.entity");
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("Hibernate.DIALECT"));
		hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("Hibernate.SHOW_SQL"));
		hibernateProperties.setProperty("hibernate.format_sql", environment.getProperty("Hibernate.FORMAT_SQL"));
		
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		return sessionFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory,DataSource dataSource)
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
}
