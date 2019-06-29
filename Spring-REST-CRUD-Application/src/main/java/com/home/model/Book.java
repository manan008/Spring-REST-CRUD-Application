package com.home.model;

import java.time.LocalDate;

public class Book 
{
	private Integer bookId ;
	private String bookName ;
	private String publisherName ;
	private LocalDate publishedDate ;
	private Float cost;
	
	public Book() {
		System.out.println("Book class Constructor called");
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
}
