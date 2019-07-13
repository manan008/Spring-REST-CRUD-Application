package com.home.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.home.dao.AuthorDAOTest;
import com.home.dao.LoginDAOTest;
import com.home.service.AuthorServiceTest;
import com.home.service.LoginServiceTest;
import com.home.validator.AuthorValidatorTest;
import com.home.validator.BookValidatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	AuthorValidatorTest.class,
	BookValidatorTest.class,
	
	LoginDAOTest.class,
	AuthorDAOTest.class,
	
	LoginServiceTest.class,
	AuthorServiceTest.class
})
public class SpringRestCrudTestSuite {

}
