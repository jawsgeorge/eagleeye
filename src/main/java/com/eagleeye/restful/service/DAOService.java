package com.eagleeye.restful.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eagleeye.restful.model.User;
import com.eagleeye.restful.model.UserDAO;
import com.eagleeye.restful.model.CustomerPayment;
import com.eagleeye.restful.web.SlotResponse;

@Repository
@Transactional
public class DAOService {
	final static Logger logger = Logger.getLogger(UserDAO.class);
	/**
	   * Return the user having the passed username.
	   */
	  public List getSlotBooking(String date) {
		 logger.debug("received the value getSlotBooking : "+date);
		  User user = null;
		  List responseList=null;
		  try
		  {
	    /*user = (User) entityManager.createQuery(
	        "from User where user_name = :userName")
	        .setParameter("userName", userName)
	        .getSingleResult();*/
			 
			  String query =" select slot.slot_id,slot.startTime,slot.endTime,booking.status,booking.date, "+
			  " ground.ground_id,ground.groundName from Slot as slot, SlotBooking as booking, "+
		      " Ground as ground where slot.slot_id=booking.slotId and "+
			  " booking.groundId = ground.ground_id and booking.date=STR_TO_DATE('"+date+"','%Y-%m-%d')";
			  logger.debug(" Query is : "+query);
			  
			  
			  List<Object[]> rows =  entityManager.createQuery(query)
				       			        .getResultList();
			  logger.debug("received the value rows : "+rows.size());
			  responseList = new ArrayList();
			  for (Object[] row : rows) {
				  SlotResponse slotResponse = new SlotResponse();
				  logger.debug("value is slot id : "+row[0]);
				  slotResponse.setSlotId((int)row[0]);
				  logger.debug("value is start time : "+row[1]);
				  slotResponse.setStartTime((String)row[1]);
				  logger.debug("value is end time : "+row[2]); 
				  slotResponse.setEndTime((String)row[2]);
				  logger.debug("value is status : "+row[3]); 
				  slotResponse.setStatus((String)row[3]);
				  logger.debug("value is booking date : "+row[4]); 
				  slotResponse.setBookingDate((Date)row[4]); 
				  logger.debug("value is ground id : "+row[5]); 
				  slotResponse.setGroundId((int)row[5]);
				  logger.debug("value is ground name : "+row[6]); 
				  slotResponse.setGroundName((String)row[6]);
				  responseList.add(slotResponse);
			  }
			 return responseList;
			  
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  
		  }
		  //return user;
		  return responseList;
	  }
	  
	  

	  
	  public void updateSlotStatus(List<Integer> ids){
		  try{
		  for(int bookid : ids){
			  String query5 = "update SlotBooking set status='booked' where book_id="+bookid;
			  int result=entityManager.createQuery(query5).executeUpdate();
			  if(result==1){
				  logger.debug("slot confirmed ");
			  }else{
				  logger.debug("slot is not booked successfully.");
			  }
			  
			  
		  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
	  }
	  
	  
	  
	  
	// Private fields
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
}