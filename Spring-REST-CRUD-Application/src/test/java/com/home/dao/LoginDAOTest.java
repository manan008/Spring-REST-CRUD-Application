package com.home.dao;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.home.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginDAOTest {
	
	@Autowired
	private LoginDAO loginDAO;
	
	@Test
	public void authSuccess()
	{
		User user = new User();
		user.setUsername("root");
		user.setPassword("root");
		Assert.assertTrue(loginDAO.auth(user)==1);
	}
	
	@Test
	public void authIncorrectPwd()
	{
		User user = new User();
		user.setUsername("root");
		user.setPassword("roo");
		Assert.assertTrue(loginDAO.auth(user)==0);
	}
	
	@Test
	public void authUserNotExist()
	{
		User user = new User();
		user.setUsername("roo");
		user.setPassword("root");
		Assert.assertTrue(loginDAO.auth(user)==-1);
	}
}
