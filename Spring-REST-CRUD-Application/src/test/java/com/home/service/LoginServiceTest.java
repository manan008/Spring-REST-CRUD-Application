package com.home.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import com.home.dao.LoginDAO;
import com.home.model.User;


@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	@Mock
	private LoginDAO loginDAO;
	
	@InjectMocks
	private LoginService loginService = new LoginServiceImpl();
	
	@Test
	public void authSuccess()
	{
		User user = new User();
		user.setUsername("root");
		user.setPassword("root");
		when(loginDAO.auth(user)).thenReturn(1);
		Assert.assertEquals("LoginService.SUCCESS", loginService.auth(user));
	}
	
	@Test
	public void authIncorrectPwd()
	{
		User user = new User();
		user.setUsername("root");
		user.setPassword("roo");
		when(loginDAO.auth(user)).thenReturn(0);
		Assert.assertEquals("LoginService.INCORRECT_PASSWORD", loginService.auth(user));
	}
	
	@Test
	public void authUserNotExist()
	{
		User user = new User();
		user.setUsername("roo");
		user.setPassword("root");
		when(loginDAO.auth(user)).thenReturn(-1);
		Assert.assertEquals("LoginService.USER_NOT_FOUND", loginService.auth(user));
	}
}
