package com.home.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.home.model.Book;

public class BookValidatorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void validateEmailIdValidEmailId() throws Exception
	{
		Book book = new Book();
		book.setBookId("B_6001");
		BookValidator.validate(book);
	}
	
	@Test
	public void validateEmailIdInvalidEmailId() throws Exception
	{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("BookValidator.INVALID_BOOKID");
		Book book = new Book();
		book.setBookId("B_601");
		BookValidator.validate(book);
	}
	
}
