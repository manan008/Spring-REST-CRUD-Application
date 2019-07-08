package com.home.validator;

import com.home.model.Author;

public class AuthorValidator {
	
	public void validate(Author author) throws Exception
	{
		if(!validateEmailId(author.getEmailId()))
			throw new Exception("AuthorValidator.INVALID_EMAILID");		
	}
	private Boolean validateEmailId(String emailId)
	{
		String emailIdRegex="[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+";
		if(emailId.matches(emailIdRegex))
		{
			return true;
		}
		return false;
	}
}
