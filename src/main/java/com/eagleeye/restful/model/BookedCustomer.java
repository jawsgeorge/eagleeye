package com.eagleeye.restful.model;

import java.sql.Date;
import java.util.List;

public class BookedCustomer implements java.io.Serializable {
	
	private long bookingReference;
	private String name;
	private long mobileNo;

	
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
	
	

}
