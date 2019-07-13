package com.home.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.entity.AuthorEntity;
import com.home.entity.BookEntity;
import com.home.model.Author;
import com.home.model.Book;

@Repository(value="authorDAO")
public class AuthorDAOImpl implements AuthorDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Boolean checkEmailAvailability(String emailId) {
		// TODO Auto-generated method stub
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
			return true;
		}
		else {
			//email does not exist
			return false;
		}
	}

	@Override
	public Integer addAuthor(Author author) {
		// TODO Auto-generated method stub
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
		System.out.println(authorId);
		return authorId;
	}

	@Override
	public Author getAuthorDetails(String emailId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
				bookEntity.setBookName(book.getBookName());
				bookEntity.setLanguage(book.getLanguage());
				bookEntity.setCost(book.getCost());
				bookEntity.setPublishedDate(book.getPublishedDate());
			}
		}
		session.persist(authorEntity);
		Integer authorId = authorEntity.getAuthorId();
		return authorId;
	}

	@Override
	public Integer deleteAuthor(Integer authorId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		/*
		 * CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		 * CriteriaQuery<AuthorEntity> criteriaQuery =
		 * criteriaBuilder.createQuery(AuthorEntity.class); Root<AuthorEntity> root =
		 * criteriaQuery.from(AuthorEntity.class); criteriaQuery.select(root);
		 * criteriaQuery.where(criteriaBuilder.equal(root.get("emailId"), emailId));
		 * Query query = session.createQuery(criteriaQuery); List<AuthorEntity>
		 * authorEntityList = query.getResultList(); if(!authorEntityList.isEmpty()) {
		 * for(AuthorEntity authorEntity : authorEntityList) {
		 * session.delete(authorEntity); return 1; } } return null;
		 */
		AuthorEntity authorEntity = session.get(AuthorEntity.class, authorId);
		session.delete(authorEntity);
		return 1;
	}

}
