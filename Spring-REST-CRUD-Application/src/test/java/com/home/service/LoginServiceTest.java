package com.home.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();	
	
	@Test
	public void authSuccess() throws Exception
	{
		User user = new User();
		user.setUsername("root");
		user.setPassword("root");
		when(loginDAO.auth(user)).thenReturn(1);
		Assert.assertEquals("LoginService.SUCCESS", loginService.auth(user));
	}
	
	@Test
	public void authIncorrectPwd() throws Exception
	{
		User user = new User();
		user.setUsername("root");
		user.setPassword("roo");
		when(loginDAO.auth(user)).thenReturn(0);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("LoginService.INCORRECT_PASSWORD");
		loginService.auth(user);
	}
	
	@Test
	public void authUserNotExist() throws Exception
	{
		User user = new User();
		user.setUsername("roo");
		user.setPassword("root");
		expectedException.expect(Exception.class);
		expectedException.expectMessage("LoginService.USER_NOT_FOUND");
		loginService.auth(user);
	}
}
