package com.home.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.home.configuration.SpringConfig;
import com.home.model.Author;
import com.home.model.Book;
import com.home.utility.Qualification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@Transactional
public class AuthorDAOTest {

	@Autowired
	private AuthorDAO authorDAO;
	
	@Test
	public void checkEmailAvailabilityAlreadyExist()
	{
		Assert.assertTrue(authorDAO.checkEmailAvailability("kyle@gmail.com"));
	}
	
	@Test
	public void checkEmailAvailabilityDoesNotExist()
	{
		Assert.assertFalse(authorDAO.checkEmailAvailability("kyle1@gmail.com"));
	}
	
	@Test
	public void addAuthorSuccess()
	{
		Author author = new Author();
		author.setAuthorName("James Gosling");
		author.setEmailId("james@gmail.com");
		author.setQualification(Qualification.MBA);
		
		List<Book> bookList = new ArrayList<Book>();
		
		Book book1 = new Book ();
		book1.setBookName("book1");
		book1.setCost(24.60f);
		book1.setLanguage("English");
		book1.setPublishedDate(LocalDate.parse( "2017-02-05" ));
		Book book2 = new Book ();
		book2.setBookName("book2");
		book2.setCost(64.60f);
		book2.setLanguage("English");
		book2.setPublishedDate(LocalDate.parse( "2018-03-17" ));
		
		bookList.add(book1);
		bookList.add(book2);
		
		author.setBookList(bookList);
		
		authorDAO.addAuthor(author);
		//after this test case is run , check in db if this record is added or not
	}
	
	@Test
	public void getAuthorDetailsSuccess()
	{
		Author author = new Author();
		author.setEmailId("james@gmail.com");
		Assert.assertNotNull(authorDAO.getAuthorDetails("kyle@gmail.com"));
	}
	
	@Test
	public void getAuthorDetailsNoAuthor()
	{
		Author author = new Author();
		author.setEmailId("james1@gmail.com");
		Assert.assertNull(authorDAO.getAuthorDetails("james@gmail.com"));
	}
	
	@Test
	public void updateAuthorDetailsSuccess()
	{
		Author author = new Author();
		author.setAuthorId(1004);
		author.setAuthorName("Kyle Simpson");
		author.setEmailId("kyletest@gmail.com");
		author.setQualification(Qualification.BBA);
		
		List<Book> bookList = new ArrayList<Book>();
		
		Book book1 = new Book ();
		book1.setBookId("B_6004");
		book1.setBookName("Understanding ECMAScript 6");
		book1.setCost(1060.20f);
		book1.setLanguage("English");
		book1.setPublishedDate(LocalDate.parse( "2017-02-05" ));
		Book book2 = new Book ();
		book2.setBookId("B_6005");
		book2.setBookName("You Don''t Know JS");
		book2.setCost(272.80f);
		book2.setLanguage("English");
		book2.setPublishedDate(LocalDate.parse( "2018-03-17" ));
		
		bookList.add(book1);
		bookList.add(book2);
		
		author.setBookList(bookList);
		
		authorDAO.updateAuthorDetails(author);
		//after this test case is run , check in db if this record is updated or not
	}
	
	@Test
	public void deleteAuthorSuccess()
	{
		Assert.assertTrue(authorDAO.deleteAuthor(1003)==1);
		//after this test case is run , check in db if this record is deleted or not
	}
}
