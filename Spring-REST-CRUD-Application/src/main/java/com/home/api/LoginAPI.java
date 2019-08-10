package com.home.api;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.model.User;
import com.home.service.LoginService;
import com.home.service.LoginServiceImpl;
import com.home.utility.ContextFactory;
import com.home.utility.LogConfig;

@CrossOrigin
@RestController
@RequestMapping("LoginAPI")
@PropertySource("classpath:/com/home/resources/configuration.properties")
public class LoginAPI {

	@Autowired
	private Environment environment;
	
	private LoginService loginService;
	
	final static Logger LOGGER = LogConfig.getLogger(LoginAPI.class);
	
	@RequestMapping(value = "auth",method = RequestMethod.POST)
	public ResponseEntity<User> authenticateUser(@RequestBody User user) throws Exception
	{
		LOGGER.info("Authentication API called");
		LOGGER.info("Entered username : "+user.getUsername());
		String status = null;
		try {
			  loginService = ContextFactory.getContext().getBean(LoginServiceImpl.class);
			  status = loginService.auth(user);
			  user.setPassword("");
			  user.setSuccessMessage(environment.getProperty(status));
			  LOGGER.info("Authentication status for "+user.getUsername()+" is "+environment.getProperty(status));
			  return new ResponseEntity<User>(user , HttpStatus.OK);
		}
		catch (Exception e) {
			user.setPassword("");
			  if(environment.getProperty(e.getMessage())==null) 
			  { 
				  status = e.getMessage();
			  } 
			  else 
			  { 
				  status = environment.getProperty(e.getMessage());
			  }
			  user.setErrorMessage(status);
			  LOGGER.info("Authentication status for "+user.getUsername()+" is "+status);
			  return new ResponseEntity<User>(user , HttpStatus.BAD_REQUEST);
		}
	}
}
