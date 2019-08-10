package com.home.service;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.home.dao.AuthorDAO;
import com.home.model.Author;
import com.home.model.Book;
import com.home.utility.LogConfig;
import com.home.validator.AuthorValidator;
import com.home.validator.BookValidator;

@Transactional(readOnly = true)
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorDAO authorDAO;
	
	final static Logger LOGGER = LogConfig.getLogger(AuthorServiceImpl.class);
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Integer addAuthor(Author author) throws Exception {
		LOGGER.info("Author Service called-Add Operation");
		AuthorValidator.validate(author.getEmailId());
		LOGGER.info(author.getEmailId()+"email Id validated");
		Boolean isEmailAlreadyAvailable = authorDAO.checkEmailAvailability(author.getEmailId());
		if(isEmailAlreadyAvailable)
			{
				//email already existing in db
				LOGGER.info(author.getEmailId()+"-AuthorService.EXISITING_EMAIL_ID");
				throw new Exception("AuthorService.EXISITING_EMAIL_ID");
			}
		Integer authorId = authorDAO.addAuthor(author);
		LOGGER.info(authorId+" identified author details successfully added");
		return authorId;
	}

	@Override
	@Transactional(readOnly = true)
	public Author getAuthorDetails(String emailId) throws Exception {
		LOGGER.info("Author Service called-Get Operation");		
		AuthorValidator.validate(emailId);
		LOGGER.info(emailId+"email Id validated");
		Author author = authorDAO.getAuthorDetails(emailId);
		if(author==null)
		{
			LOGGER.info(emailId+"-AuthorService.AUTHOR_NOT_FOUND");
			throw new Exception("AuthorService.AUTHOR_NOT_FOUND");
		}
		return author;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Integer updateAuthorDetails(Author author) throws Exception {
		LOGGER.info("Author Service called-Update Operation");
		for(Book book : author.getBookList()) 
		{ 
			BookValidator.validate(book);
			LOGGER.info("Book with id-"+book.getBookId()+" vaidated");
		}
		AuthorValidator.validate(author.getEmailId());
		LOGGER.info(author.getEmailId()+"email Id validated");
		if(!authorDAO.getAuthorEmailByAuthorId(author.getAuthorId()).equals(author.getEmailId()))
		{
			//email of author is updated/changed
			Boolean isEmailAlreadyAvailable = authorDAO.checkEmailAvailability(author.getEmailId());
			if(isEmailAlreadyAvailable)
			{
				//email already existing in db
				LOGGER.info(author.getEmailId()+"-AuthorService.EXISITING_EMAIL_ID");
				throw new Exception("AuthorService.EXISITING_EMAIL_ID");
			}
		}
		Integer authorId = authorDAO.updateAuthorDetails(author);
		LOGGER.info(authorId+" identified author details successfully updated");
		return authorId;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String deleteAuthor(Integer authorId) {
		LOGGER.info("Author Service called-Delete Operation");
		Integer deleteStatus = authorDAO.deleteAuthor(authorId);
		if(deleteStatus == 1) {
			LOGGER.info("Details of author with ID-"+authorId+" is deleted successfully");
			return "AuthorService.DELETE_SUCCESS";
		}
		return null;
	}

}
