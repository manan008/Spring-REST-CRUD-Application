package com.home.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class AuthorValidatorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void validateEmailIdValidEmailId() throws Exception
	{
		AuthorValidator.validate("james@gmail.com");
	}
	
	@Test
	public void validateEmailIdInvalidEmailId() throws Exception
	{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AuthorValidator.INVALID_EMAILID");
		AuthorValidator.validate("jamesgmail.com");
	}
	
}
