package com.home.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.home.dao.LoginDAO;
import com.home.model.User;

public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDAO loginDao;
	
	@Override
	public String auth(User user) {
		// TODO Auto-generated method stub
		Integer authStatus = loginDao.auth(user);
		if(authStatus == 1)
		{
			return "LoginService.SUCCESS";
		}
		else if(authStatus == 0)
		{
			return "LoginService.INCORRECT_PASSWORD";
		}
		else
		{
			return "LoginService.USER_NOT_FOUND";
		}
	}

}
