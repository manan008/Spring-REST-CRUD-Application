package com.home.service;


import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.dao.LoginDAO;
import com.home.model.User;
import com.home.utility.LogConfig;

@Transactional(readOnly = true)
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDAO loginDao;
	
	final static Logger LOGGER = LogConfig.getLogger(LoginServiceImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public String auth(User user) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Login Service called");
		Integer authStatus = loginDao.auth(user);
		LOGGER.info("Login Service returned auth status -"+authStatus);
		if(authStatus == 1)
		{
			return "LoginService.SUCCESS";
		}
		else if(authStatus == 0)
		{
			throw new Exception("LoginService.INCORRECT_PASSWORD");
		}
		else
		{
			throw new Exception("LoginService.USER_NOT_FOUND");
		}
	}

}
