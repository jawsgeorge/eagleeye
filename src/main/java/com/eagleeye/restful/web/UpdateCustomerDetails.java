package com.eagleeye.restful.web;



public class UpdateCustomerDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8885634357187144699L;
	
	
	private String Name;
	
	
	private int amount;
	
	
	private long mobileNumber;
	
	
	private String mode;
	
	private int bookingReference;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(int bookingReference) {
		this.bookingReference = bookingReference;
	}
	
	

}
