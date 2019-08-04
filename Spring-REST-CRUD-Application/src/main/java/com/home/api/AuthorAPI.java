package com.home.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.model.Author;
import com.home.service.AuthorService;
import com.home.service.AuthorServiceImpl;
import com.home.utility.ContextFactory;

@CrossOrigin
@RestController
@RequestMapping("AuthorAPI")
@PropertySource("classpath:/com/home/resources/configuration.properties")
public class AuthorAPI {

	@Autowired
	private Environment environment;
	
	private AuthorService authorService;
	
	@RequestMapping(value = "addAuthor",method = RequestMethod.POST)
	public ResponseEntity<Author> addAuthor(@RequestBody Author author) throws Exception
	{
		try {
			authorService = ContextFactory.getContext().getBean(AuthorServiceImpl.class);
			Integer authorId = authorService.addAuthor(author);
			author.setAuthorId(authorId);
			author.setSuccessMessage(author.getAuthorName()+environment.getProperty("AuthorAPI.ADD_SUCCESS"));
			return new ResponseEntity<Author>(author , HttpStatus.OK);
		}
		catch (Exception e) 
		{
			  if(environment.getProperty(e.getMessage())==null)
			  { 
				  author.setErrorMessage(e.getMessage());
			  } 
			  else 
			  { 
				  author.setErrorMessage(environment.getProperty(e.getMessage()));
			  }
			  return new ResponseEntity<Author>(author , HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "getAuthor",method = RequestMethod.GET)
	public ResponseEntity<Author> getAuthorDetails(@RequestParam String email) throws Exception
	{
		try {
			authorService = ContextFactory.getContext().getBean(AuthorServiceImpl.class);
			Author authorDetails = authorService.getAuthorDetails(email);
			return new ResponseEntity<Author>(authorDetails , HttpStatus.OK);
		}
		catch (Exception e) 
		{
			Author authorDetails = new Author();
			if(environment.getProperty(e.getMessage())==null)
			{ 
				authorDetails.setErrorMessage(e.getMessage());
			} 
			else 
			{ 
				authorDetails.setErrorMessage(environment.getProperty(e.getMessage()));
			}
			return new ResponseEntity<Author>(authorDetails , HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "updateAuthor",method = RequestMethod.POST)
	public ResponseEntity<Author> updateAuthorDetails(@RequestBody Author author) throws Exception
	{
		try {
			authorService = ContextFactory.getContext().getBean(AuthorServiceImpl.class);
			Integer authorId = authorService.updateAuthorDetails(author);
			author.setSuccessMessage(environment.getProperty("AuthorAPI.DETAILS_UPDATE_SUCCESS")+" "+author.getAuthorName()+" (ID:"+authorId+")");
			return new ResponseEntity<Author>(author , HttpStatus.OK);
		}
		catch (Exception e) 
		{
			  if(environment.getProperty(e.getMessage())==null)
			  { 
				  author.setErrorMessage(e.getMessage());
			  } 
			  else 
			  { 
				  author.setErrorMessage(environment.getProperty(e.getMessage()));
			  }
			  return new ResponseEntity<Author>(author , HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "deleteAuthor",method = RequestMethod.POST)
	public ResponseEntity<Author> deleteAuthor(@RequestBody Author author) throws Exception
	{
		try {
			authorService = ContextFactory.getContext().getBean(AuthorServiceImpl.class);
			String deleteStatus = authorService.deleteAuthor(author.getAuthorId());
			author.setSuccessMessage(author.getAuthorName()+environment.getProperty(deleteStatus));
			return new ResponseEntity<Author>(author , HttpStatus.OK);
		}
		catch (Exception e) 
		{
			  if(environment.getProperty(e.getMessage())==null)
			  { 
				  author.setErrorMessage(e.getMessage());
			  } 
			  else 
			  { 
				  author.setErrorMessage(environment.getProperty(e.getMessage()));
			  }
			  return new ResponseEntity<Author>(author , HttpStatus.BAD_REQUEST);
		}
	}
	
}
