package com.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.home.dao.AuthorDAO;
import com.home.model.Author;
import com.home.model.Book;
import com.home.validator.AuthorValidator;
import com.home.validator.BookValidator;

@Transactional(readOnly = true)
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorDAO authorDAO;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Integer addAuthor(Author author) throws Exception {
		// TODO Auto-generated method stub
		AuthorValidator.validate(author.getEmailId());
		Boolean isEmailAlreadyAvailable = authorDAO.checkEmailAvailability(author.getEmailId());
		if(isEmailAlreadyAvailable)
		{
			//email already existing in db
			throw new Exception("AuthorService.EXISITING_EMAIL_ID");
		}
		Integer authorId = authorDAO.addAuthor(author);
		return authorId;
	}

	@Override
	@Transactional(readOnly = true)
	public Author getAuthorDetails(String emailId) throws Exception {
		// TODO Auto-generated method stub
		
		AuthorValidator.validate(emailId);
		Author author = authorDAO.getAuthorDetails(emailId);
		if(author==null)
		{
			throw new Exception("AuthorService.AUTHOR_NOT_FOUND");
		}
		return author;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Integer updateAuthorDetails(Author author) throws Exception {
		// TODO Auto-generated method stub
		for(Book book : author.getBookList()) 
		{ 
			BookValidator.validate(book); 
		}
		AuthorValidator.validate(author.getEmailId());
		Boolean isEmailAlreadyAvailable = authorDAO.checkEmailAvailability(author.getEmailId());
		if(isEmailAlreadyAvailable)
		{
			//email already existing in db
			throw new Exception("AuthorService.EXISITING_EMAIL_ID");
		}
		Integer authorId = authorDAO.updateAuthorDetails(author);
		return authorId;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Integer deleteAuthor(Integer authorId) {
		// TODO Auto-generated method stub
		Integer deleteStatus = authorDAO.deleteAuthor(authorId);
		return deleteStatus;
	}

}
