package com.home.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.home.utility.StringPrefixedSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;	

@Entity
@Table(name="BOOK")
public class BookEntity {
	@Id
	@Column(name="bookid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@GenericGenerator(
	        name = "book_seq", 
	        strategy = "com.home.utility.StringPrefixedSequenceIdGenerator",
	        parameters = {
	        		@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
	                @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
	                @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	        		@Parameter(name="sequence_name", value="HIBERNATE_SEQUENCE")
	        })
	private String bookId ;
	@Column(name="bookname")
	private String bookName ;
	@Column(name="language")
	private String language ;
	@Column(name="publisheddate")
	private LocalDate publishedDate ;
	@Column(name="cost")
	private Float cost;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
