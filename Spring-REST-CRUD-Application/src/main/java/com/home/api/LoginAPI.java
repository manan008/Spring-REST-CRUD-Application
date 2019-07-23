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

import com.home.model.Author;
import com.home.model.User;
import com.home.service.LoginServiceImpl;
import com.home.utility.ContextFactory;

@CrossOrigin
@RestController
@RequestMapping("LoginAPI")
@PropertySource("classpath:/com/home/resources/configuration.properties")
public class LoginAPI {

	@Autowired
	private Environment environment;
	
//	@RequestMapping(value = "auth",method = RequestMethod.POST)
//	public ResponseEntity<String> authenticateUser(@RequestBody User user) throws Exception
	@RequestMapping(value = "auth",method = RequestMethod.GET)
	public ResponseEntity<User> authenticateUser() throws Exception
	{
		String status = null;
		try {
			
			
			/*
			 * LoginServiceImpl loginServiceImpl =
			 * ContextFactory.getContext().getBean(LoginServiceImpl.class);
			 * loginServiceImpl.auth(user); System.out.println(status);
			 * System.out.println(environment.getProperty(status));
			 * 
			 * return new ResponseEntity<String>(status , HttpStatus.OK);
			 */
			 
			
			  User user = new User(); user.setUsername("root"); user.setPassword("root");
			  return new ResponseEntity<User>(user , HttpStatus.OK);
			 
		}
		catch (Exception e) {
			// TODO: handle exception
			/*
			 * User user = new User(); System.out.println(e.getMessage());
			 * System.out.println(environment.getProperty(e.getMessage()));
			 * if(environment.getProperty(e.getMessage())==null) { status = e.getMessage();
			 * } else { status = environment.getProperty(e.getMessage()); }
			 */
			//return new ResponseEntity<String>("Bye" , HttpStatus.BAD_REQUEST);
			return null;
		}
	}
	
}
