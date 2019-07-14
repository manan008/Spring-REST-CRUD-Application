package com.home.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.home.utility.Qualification;



@Entity
@Table(name="AUTHOR")
@GenericGenerator(name="idGen",strategy = "increment")
public class AuthorEntity {
	@Id
	@Column(name="authorid")
	@GeneratedValue(generator = "idGen")
	private Integer authorId ;
	@Column(name="authorname")
	private String authorName ;
	@Column(name="emailid")
	private String emailId ;
	@Column(name="qualification")
	@Enumerated(EnumType.STRING)
	private Qualification qualification ;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="AUTHOR_BOOK",
	joinColumns = @JoinColumn(name="author_id",referencedColumnName = "authorid"),
	inverseJoinColumns = @JoinColumn(name="book_id",referencedColumnName = "bookid"))
	private List<BookEntity> bookList ;
	
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<BookEntity> getBookList() {
		return bookList;
	}
	public void setBookList(List<BookEntity> bookList) {
		this.bookList = bookList;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
	
}
