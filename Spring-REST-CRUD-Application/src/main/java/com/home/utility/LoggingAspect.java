package com.home.utility;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect 
{
	@AfterThrowing(pointcut="execution(* com.home.service.*Impl*.*(..))")
	public void logExceptionFromService(Exception exception) throws Exception
	{
		log(exception);
	}
	
	@AfterThrowing(pointcut="execution(* com.home.dao.*Impl*.*(..))")
	public void logExceptionFromDao(Exception exception) throws Exception
	{
		log(exception);
	}

	private void log(Exception exception) {
		// TODO Auto-generated method stub
		Logger logger = LogConfig.getLogger(this.getClass());
		logger.error(exception.getMessage());
	}
}
