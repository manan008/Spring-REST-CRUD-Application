package com.home.api;

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

@CrossOrigin
@RestController
@RequestMapping("LoginAPI")
@PropertySource("classpath:/com/home/resources/configuration.properties")
public class LoginAPI {

	@Autowired
	private Environment environment;
	
	private LoginService loginService;
	
	@RequestMapping(value = "auth",method = RequestMethod.POST)
	public ResponseEntity<String> authenticateUser(@RequestBody User user) throws Exception
	{
		String status = null;
		try {
			  loginService = ContextFactory.getContext().getBean(LoginServiceImpl.class);
			  status = loginService.auth(user); 
			  return new ResponseEntity<String>(environment.getProperty(status) , HttpStatus.OK);
		}
		catch (Exception e) {
			
			  if(environment.getProperty(e.getMessage())==null) 
			  { 
				  status = e.getMessage();
			  } 
			  else 
			  { 
				  status = environment.getProperty(e.getMessage());
			  }
			  return new ResponseEntity<String>(status , HttpStatus.BAD_REQUEST);
		}
	}
}
