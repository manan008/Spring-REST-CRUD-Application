package com.home.service;


import com.home.model.Author;

public interface AuthorService {
	public Integer addAuthor(Author author) throws Exception;
	public Author getAuthorDetails(String emailId) throws Exception;
	public Integer updateAuthorDetails(Author author) throws Exception;
	public String deleteAuthor(Integer authorId);
}
