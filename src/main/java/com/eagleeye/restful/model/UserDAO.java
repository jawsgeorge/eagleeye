package com.eagleeye.restful.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eagleeye.restful.controller.EmployeeController;



@Repository
@Transactional
public class UserDAO {

	final static Logger logger = Logger.getLogger(UserDAO.class);
	/**
	   * Return the user having the passed username.
	   */
	  public User getByUserName(User userObj) {
		  logger.debug("received the value : "+userObj.getUserName());
		  User user = null;
		  try
		  {
	    /*user = (User) entityManager.createQuery(
	        "from User where user_name = :userName")
	        .setParameter("userName", userName)
	        .getSingleResult();*/
			  Query query = entityManager.createQuery(
				        "from User where user_name = :userName");
			  query.setParameter("userName", userObj.getUserName());
			 // query.setParameter("password", userObj.)
		  }
		  catch(Exception e)
		  {
			  e.getMessage();
		  }
		  return user;
	  }
	// Private fields
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
}
