package com.home.service;

import java.util.List;

import com.home.model.Book;

public interface BookCrudService {
	
	public Integer addBook(Book book);
	public List<Book> viewBooks();
	public Integer updateBook(Book book);
	public Integer deleteBook(Integer bookId);
}
