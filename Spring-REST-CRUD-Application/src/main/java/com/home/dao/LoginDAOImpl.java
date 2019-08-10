package com.home.dao;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.entity.UserEntity;
import com.home.model.User;
import com.home.utility.LogConfig;

@Repository(value="loginDAO")
public class LoginDAOImpl implements LoginDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	final static Logger LOGGER = LogConfig.getLogger(LoginDAOImpl.class);
	
	@Override
	public Integer auth(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		LOGGER.info("Login Dao called");
		UserEntity userEntity = session.get(UserEntity.class, user.getUsername());
		LOGGER.info("Entity retrieved for "+user.getUsername()+" is "+userEntity);
		if(userEntity!= null)
		{
			if(userEntity.getPassword().equals(user.getPassword()))
			{
				//auth success
				return 1;
			}
			else {
				//incorrect password
				return 0;
			}
		}
		else {
			//user does not exist
			return -1;
		}
	}

}
