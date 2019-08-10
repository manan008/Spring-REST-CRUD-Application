package com.home.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.entity.AuthorEntity;
import com.home.entity.BookEntity;
import com.home.model.Author;
import com.home.model.Book;
import com.home.utility.LogConfig;

@Repository(value="authorDAO")
public class AuthorDAOImpl implements AuthorDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	final static Logger LOGGER = LogConfig.getLogger(AuthorDAOImpl.class);
	
	@Override
	public Boolean checkEmailAvailability(String emailId) {
		LOGGER.info("Check if "+emailId+" already exist in db or not");
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<AuthorEntity> criteriaQuery = criteriaBuilder.createQuery(AuthorEntity.class);
		Root<AuthorEntity> root = criteriaQuery.from(AuthorEntity.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("emailId"), emailId));
		Query query = session.createQuery(criteriaQuery);
		List<AuthorEntity> authorEntityList = query.getResultList();
		if(!authorEntityList.isEmpty())
		{
			//email already exist
			LOGGER.info("Email - "+emailId+" already exist");
			return true;
		}
		else {
			//email does not exist
			LOGGER.info("Email - "+emailId+" does not exist");
			return false;
		}
	}
	
	@Override
	public String getAuthorEmailByAuthorId(Integer authorId) {
		Session session = sessionFactory.getCurrentSession();
		AuthorEntity authorEntity = session.get(AuthorEntity.class, authorId);
		LOGGER.info("Author Entity for Author ID - "+authorId+" : "+authorEntity);
		if(authorEntity!=null && (!authorEntity.getEmailId().isEmpty() && !authorEntity.getEmailId().equals("")))
		{
			LOGGER.info("Email ID for Author ID - "+authorId+" : "+authorEntity.getEmailId());
			return authorEntity.getEmailId();
		}
		return null;
	}

	@Override
	public Integer addAuthor(Author author) {
		LOGGER.info("Adding author details");
		Session session = sessionFactory.getCurrentSession();
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setAuthorName(author.getAuthorName());
		authorEntity.setEmailId(author.getEmailId());
		authorEntity.setQualification(author.getQualification());
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		for(Book book : author.getBookList())
		{
			BookEntity bookEntity = new BookEntity();
			bookEntity.setBookName(book.getBookName());
			bookEntity.setLanguage(book.getLanguage());
			bookEntity.setCost(book.getCost());
			bookEntity.setPublishedDate(book.getPublishedDate());
			bookEntityList.add(bookEntity);
		}
		authorEntity.setBookList(bookEntityList);
		Integer authorId = (Integer)session.save(authorEntity);
		//in case of addition failure above statement will throw exception
		//If the exception is not thrown, then it means the operation is successful.
		LOGGER.info("Adding author success");
		return authorId;
	}

	@Override
	public Author getAuthorDetails(String emailId) {
		LOGGER.info("Getting author details");
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<AuthorEntity> criteriaQuery = criteriaBuilder.createQuery(AuthorEntity.class);
		Root<AuthorEntity> root = criteriaQuery.from(AuthorEntity.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("emailId"), emailId));
		Query query = session.createQuery(criteriaQuery);
		List<AuthorEntity> authorEntityList = query.getResultList();
		if(!authorEntityList.isEmpty())
		{
			for(AuthorEntity authorEntity : authorEntityList)
			{
				Author author = new Author();
				author.setAuthorId(authorEntity.getAuthorId());
				author.setAuthorName(authorEntity.getAuthorName());
				author.setEmailId(authorEntity.getEmailId());
				author.setQualification(authorEntity.getQualification());
				List<Book> bookList = new ArrayList<Book>();
				for(BookEntity bookEntity : authorEntity.getBookList())
				{
					Book book = new Book();
					
					book.setBookId(bookEntity.getBookId());
					book.setBookName(bookEntity.getBookName());
					book.setLanguage(bookEntity.getLanguage());
					book.setCost(bookEntity.getCost());
					book.setPublishedDate(bookEntity.getPublishedDate());
					
					bookList.add(book);
				}
				author.setBookList(bookList);
				return author;
			}
		}
		return null;
	}

	@Override
	public Integer updateAuthorDetails(Author author) {
		LOGGER.info("Updating author details for "+author.getEmailId());
		Session session = sessionFactory.getCurrentSession();
		AuthorEntity authorEntity = session.get(AuthorEntity.class, author.getAuthorId());
		if(authorEntity!=null)
		{
			authorEntity.setAuthorName(author.getAuthorName());
			authorEntity.setEmailId(author.getEmailId());
			authorEntity.setQualification(author.getQualification());
			for(Book book : author.getBookList())
			{
				BookEntity bookEntity = session.get(BookEntity.class, book.getBookId());
				if(bookEntity!=null)
				{
					bookEntity.setBookName(book.getBookName());
					bookEntity.setLanguage(book.getLanguage());
					bookEntity.setCost(book.getCost());
					bookEntity.setPublishedDate(book.getPublishedDate());
				}
			}
		}
		session.persist(authorEntity);
		//in case of updating failure above statement will throw exception
		//If the exception is not thrown, then it means the operation is successful.
		Integer authorId = authorEntity.getAuthorId();
		LOGGER.info("Update Success for "+authorId);
		return authorId;
	}

	@Override
	public Integer deleteAuthor(Integer authorId) {
		LOGGER.info("Deleting author details for "+authorId);
		Session session = sessionFactory.getCurrentSession();
		AuthorEntity authorEntity = session.get(AuthorEntity.class, authorId);
		session.delete(authorEntity);	
		//in case of deletion failure above statement will throw exception
		//If the exception is not thrown, then it means the operation is successful.
		LOGGER.info("Delete Success for - "+authorId);
		return 1;
	}

}
