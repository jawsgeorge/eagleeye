package com.eagleeye.restful.model;

import java.sql.Date;
import java.util.List;

public class BookedCustomer implements java.io.Serializable {
	
	private long bookingReference;
	private String name;
	private long mobileNo;
//	private String startTime;
//	private String endTime;
//	private Date dateOfBooking;
//	private String groundName;
//	private String place;
	private List<SlotBooking> bookings;
	
	private String transactionStatus;
	private int pendingTransaction;
	public long getBookingReference() {
		return bookingReference;
	}
	public void setBookingReference(long bookingReference) {
		this.bookingReference = bookingReference;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
//	public String getStartTime() {
//		return startTime;
//	}
//	public void setStartTime(String startTime) {
//		this.startTime = startTime;
//	}
//	public String getEndTime() {
//		return endTime;
//	}
//	public void setEndTime(String endTime) {
//		this.endTime = endTime;
//	}
//	public Date getDateOfBooking() {
//		return dateOfBooking;
//	}
//	public void setDateOfBooking(Date dateOfBooking) {
//		this.dateOfBooking = dateOfBooking;
//	}
//	public String getGroundName() {
//		return groundName;
//	}
//	public void setGroundName(String groundName) {
//		this.groundName = groundName;
//	}
//	public String getPlace() {
//		return place;
//	}
//	public void setPlace(String place) {
//		this.place = place;
//	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public int getPendingTransaction() {
		return pendingTransaction;
	}
	public void setPendingTransaction(int pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}
	public List<SlotBooking> getBookings() {
		return bookings;
	}
	public void setBookings(List<SlotBooking> bookings) {
		this.bookings = bookings;
	}
	
	

}
