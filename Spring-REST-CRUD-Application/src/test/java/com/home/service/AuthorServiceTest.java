package com.home.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.home.dao.AuthorDAO;
import com.home.model.Author;
import com.home.model.Book;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

	@Mock
	private AuthorDAO authorDAO;
	
	@InjectMocks
	private AuthorService authorService = new AuthorServiceImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void addAuthorSuccess() throws Exception {
		Author author = new Author();
		author.setAuthorName("James Gosling");
		author.setEmailId("james@gmail.com");
		author.setQualification("MBA");
		
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

		when(authorDAO.addAuthor(author)).thenReturn(1010);
		Assert.assertTrue(authorService.addAuthor(author)==1010);
	}
	
	@Test
	public void addAuthorInvalidEmailId() throws Exception {
		Author author = new Author();
		author.setAuthorName("James Gosling");
		author.setEmailId("james@gmail.");
		author.setQualification("MBA");
		
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

		expectedException.expect(Exception.class);
		expectedException.expectMessage("AuthorValidator.INVALID_EMAILID");
		
		when(authorDAO.addAuthor(author)).thenReturn(1010);
		
		authorService.addAuthor(author);
	}
	
	@Test
	public void addAuthorExistingEmail() throws Exception {
		Author author = new Author();
		author.setAuthorName("James Gosling");
		author.setEmailId("james@gmail.com");
		author.setQualification("MBA");
		
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

		expectedException.expect(Exception.class);
		expectedException.expectMessage("AuthorService.EXISITING_EMAIL_ID");
		
		when(authorDAO.checkEmailAvailability(author.getEmailId())).thenReturn(true);
		
		authorService.addAuthor(author);
	}
	
	@Test
	public void getAuthorDetailsSuccess() throws Exception {
		Author author = new Author();
		author.setEmailId("james@gmail.com");
		when(authorDAO.getAuthorDetails("james@gmail.com")).thenReturn(author);
		Assert.assertEquals(authorService.getAuthorDetails("james@gmail.com"), author);
	}
	
	@Test
	public void getAuthorDetailsInvalidEmailId() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AuthorValidator.INVALID_EMAILID");
		
		authorService.getAuthorDetails("james@gmail.");
	}
	
	@Test
	public void getAuthorDetailsNoSuchAuthor() throws Exception {
		when(authorDAO.getAuthorDetails("james@gmail.com")).thenReturn(null);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AuthorService.AUTHOR_NOT_FOUND");
		authorService.getAuthorDetails("james@gmail.com");
	}
	
	@Test
	public void updateAuthorDetailsSuccess() throws Exception {
		Author author = new Author();
		author.setAuthorId(1010);
		author.setAuthorName("James Gosling");
		author.setEmailId("james@gmail.com");
		author.setQualification("MBA");
		
		List<Book> bookList = new ArrayList<Book>();
		
		Book book1 = new Book ();
		book1.setBookId("B_6006");
		book1.setBookName("book1");
		book1.setCost(24.60f);
		book1.setLanguage("English");
		book1.setPublishedDate(LocalDate.parse( "2017-02-05" ));
		Book book2 = new Book ();
		book2.setBookId("B_6007");
		book2.setBookName("book2");
		book2.setCost(64.60f);
		book2.setLanguage("English");
		book2.setPublishedDate(LocalDate.parse( "2018-03-17" ));
		
		bookList.add(book1);
		bookList.add(book2);
		
		author.setBookList(bookList);

		when(authorDAO.updateAuthorDetails(author)).thenReturn(1010);
		Assert.assertTrue(authorService.updateAuthorDetails(author)==1010);
	}
	
	@Test
	public void updateAuthorDetailsInvalidBookId() throws Exception {
		Author author = new Author();
		author.setAuthorId(1010);
		author.setAuthorName("James Gosling");
		author.setEmailId("james@gmail.com");
		author.setQualification("MBA");
		
		List<Book> bookList = new ArrayList<Book>();
		
		Book book1 = new Book ();
		book1.setBookId("B6006");
		book1.setBookName("book1");
		book1.setCost(24.60f);
		book1.setLanguage("English");
		book1.setPublishedDate(LocalDate.parse( "2017-02-05" ));
		Book book2 = new Book ();
		book2.setBookId("B_6007");
		book2.setBookName("book2");
		book2.setCost(64.60f);
		book2.setLanguage("English");
		book2.setPublishedDate(LocalDate.parse( "2018-03-17" ));
		
		bookList.add(book1);
		bookList.add(book2);
		
		author.setBookList(bookList);

		expectedException.expect(Exception.class);
		expectedException.expectMessage("BookValidator.INVALID_BOOKID");
		
		when(authorDAO.updateAuthorDetails(author)).thenReturn(1010);
		Assert.assertTrue(authorService.updateAuthorDetails(author)==1010);
	}
	
	@Test
	public void updateAuthorDetailsInvalidEmailId() throws Exception {
		Author author = new Author();
		author.setAuthorId(1010);
		author.setAuthorName("James Gosling");
		author.setEmailId("james@gmail.com");
		author.setQualification("MBA");
		
		List<Book> bookList = new ArrayList<Book>();
		
		Book book1 = new Book ();
		book1.setBookId("B_6006");
		book1.setBookName("book1");
		book1.setCost(24.60f);
		book1.setLanguage("English");
		book1.setPublishedDate(LocalDate.parse( "2017-02-05" ));
		Book book2 = new Book ();
		book2.setBookId("B_6007");
		book2.setBookName("book2");
		book2.setCost(64.60f);
		book2.setLanguage("English");
		book2.setPublishedDate(LocalDate.parse( "2018-03-17" ));
		
		bookList.add(book1);
		bookList.add(book2);
		
		author.setBookList(bookList);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AuthorService.EXISITING_EMAIL_ID");
		
		when(authorDAO.checkEmailAvailability(author.getEmailId())).thenReturn(true);
		Assert.assertTrue(authorService.updateAuthorDetails(author)==1010);
	}
	
	@Test
	public void deleteAuthorSuccess() throws Exception {
		when(authorDAO.deleteAuthor(1001)).thenReturn(1);
		authorService.deleteAuthor(1001);
	}
}
