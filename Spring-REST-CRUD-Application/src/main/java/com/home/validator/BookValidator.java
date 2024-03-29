package com.home.validator;

import com.home.model.Book;

public class BookValidator {
	public static void validate(Book book) throws Exception
	{
		if(!validateBookId(book.getBookId()))
			throw new Exception("BookValidator.INVALID_BOOKID");
			
	}
	private static Boolean validateBookId(String bookId)
	{
		String bookIdRegex="B_6[0-9]{3,}";
		if(bookId.matches(bookIdRegex))
		{
			return true;
		}
		return false;
	}
}
