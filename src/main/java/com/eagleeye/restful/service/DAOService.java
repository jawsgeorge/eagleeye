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
import com.eagleeye.restful.model.CustomerBooking;
import com.eagleeye.restful.model.CustomerPayment;
import com.eagleeye.restful.web.SlotDetailsResponse;
import com.eagleeye.restful.web.SlotFinalRespnse;
import com.eagleeye.restful.web.SlotRequest;
import com.eagleeye.restful.web.SlotResponse;

@Repository
@Transactional
public class DAOService {
	final static Logger logger = Logger.getLogger(UserDAO.class);
	/**
	   * Return the user having the passed username.
	   */
	  public SlotFinalRespnse getSlotBooking(SlotRequest slotRequest) {
		 logger.debug("received the value getSlotBooking : "+slotRequest.getBookDate());
		  User user = null;
		  SlotFinalRespnse responseFinal = new SlotFinalRespnse();
		  try
		  {
	    /*user = (User) entityManager.createQuery(
	        "from User where user_name = :userName")
	        .setParameter("userName", userName)
	        .getSingleResult();*/
			  
			  String groundQuery =" select mast.master_ground_id,mast.master_ground_name, "
					  +" ground.ground_id,ground.ground_name,ground.size,ground.type from eagleeye.t_master_ground mast,eagleeye.t_ground ground "
					  +"where mast.master_ground_id="+slotRequest.getMasterGroundId()+" and mast.master_ground_id=ground.master_ground_id";
			 
			  
			  List<Object[]> rowsMaster =  entityManager.createNativeQuery(groundQuery)
				       			        .getResultList();
			  List responseList=new ArrayList();
			  for (Object[] rowMaster : rowsMaster) {
				 
				  SlotDetailsResponse slotDetailsResponse = new SlotDetailsResponse();
				  slotDetailsResponse.setMasterGroundId((int)rowMaster[0]);
				  slotDetailsResponse.setGroundId((int)rowMaster[2]);
				  slotDetailsResponse.setGroundConfigCode((String)rowMaster[4]);
				  slotDetailsResponse.setGroundData((String)rowMaster[5]);

				  String query =" select slot.slot_id,slot.start_time,slot.end_time,booking.status,booking.date, "+
				  " ground.ground_id,ground.ground_name from eagleeye.t_slot as slot, eagleeye.t_slot_booking as booking, "+
			      " eagleeye.t_ground as ground where slot.slot_id=booking.slot_id and booking.ground_id = ground.ground_id  "+
				  " and booking.date=STR_TO_DATE('"+slotRequest.getBookDate()+"','%Y-%m-%d') and booking.ground_id="+slotDetailsResponse.getGroundId();
			      
				  logger.debug(" Query is : "+query);
				  
						  List<Object[]> rows =  entityManager.createNativeQuery(query)
							       			        .getResultList();
						  logger.debug("received the value rows : "+rows.size());
						  List slotList = new ArrayList();
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
							  slotList.add(slotResponse);
						  }
						  slotDetailsResponse.setSlots(slotList);
						  responseList.add(slotDetailsResponse);
			  }
			  
			  responseFinal.setSlotDetails(responseList);
			 return responseFinal;
			  
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  
		  }
		  //return user;
		  return responseFinal;
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
	  
	  public  List<Object[]> getCustomerByMobile(long mobileNo){
		  String query6 = "select bookingReference from CustomerBooking where mobileNumber="+mobileNo;
		  List<Object[]> bookRefs=  entityManager.createQuery(query6).getResultList();
		  return bookRefs;
	  }
	  
	  public int updateCustomer( int amount, CustomerBooking customer){
		  
		  int pendingAmount=customer.getPendingTransaction()-amount;
		  String transactionStatus=""; 
		  if(pendingAmount==0){
			  transactionStatus="'cleared'";
		  }else if (pendingAmount>0){
			  transactionStatus="'pending'";
		  }
		  
		 
		  String query8 = "update CustomerBooking set pendingTransaction="+pendingAmount+","+" transactionStatus="+transactionStatus+" where bookingReference="+customer.getBookingReference();
		 
		
		  
		  int result1=entityManager.createQuery(query8).executeUpdate();
		  
		  if(result1==1){
			  logger.debug("Customer booking table updated successfully ");
			  return customer.getBookingReference();
		  }else{
			  logger.debug("Customer booking table not updated successfully ");
			  return 0;
		  }
		  
		 
		  
	  }
	  
	/*  public int getCustomerByReference(long referenceNo){
		  String query6 = "select cb.customer_name,cb.mobile_number,cb.address,cb.amount,"+
		  " csbm.slot_booking_book_id,cp.name,cp.amount as camount,cp.mobile_number as mnumber,"+
		  " cp.mode from eagleeye.t_customer_booking as cb,t_customer_booking_slot_booking as csbm,t_customer_payment as cp"+
		  " where cb.booking_reference=cp.booking_reference and cb.booking_reference=csbm.customer_booking_booking_reference="+referenceNo;
		  int bookRef= (int) entityManager.createNativeQuery(query6).getResultList();
				  return bookRef;
	  }*/
	// Private fields
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
}