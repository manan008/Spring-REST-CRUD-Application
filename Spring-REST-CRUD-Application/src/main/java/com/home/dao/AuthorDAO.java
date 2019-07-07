package com.home.dao;

import com.home.model.Author;

public interface AuthorDAO {
	public Boolean checkEmailAvailability(String emailId);
//	public Boolean checkBookAvailability(String bookId);
	public Integer addAuthor(Author author);
	public Author getAuthorDetails(String emailId);
	public Integer updateAuthorDetails(Author author);
	public Integer deleteAuthor(Integer authorId);
}
