package com.home.validator;


public class AuthorValidator {
	
	public static void validate(String emailId) throws Exception
	{
		if(!validateEmailId(emailId))
			throw new Exception("AuthorValidator.INVALID_EMAILID");		
	}
	private static Boolean validateEmailId(String emailId)
	{
		String emailIdRegex="[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+";
		if(emailId.matches(emailIdRegex))
		{
			return true;
		}
		return false;
	}
}
