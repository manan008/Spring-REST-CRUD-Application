package com.home.dao;

import java.util.List;

import com.home.model.Book;

public interface BookCrudDao {
	public Integer addBook(Book book);
	public List<Book> viewBooks();
	public Integer updateBook(Book book);
}
