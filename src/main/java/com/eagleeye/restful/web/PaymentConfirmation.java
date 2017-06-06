package com.eagleeye.restful.web;

import java.sql.Date;
import java.util.List;

public class PaymentConfirmation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6620936915724808094L;
	private String referenceNumber;
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public List getGrounds() {
		return grounds;
	}
	public void setGrounds(List grounds) {
		this.grounds = grounds;
	}
	public List getSlots() {
		return slots;
	}
	public void setSlots(List slots) {
		this.slots = slots;
	}
	private String place;
	private List grounds;
	private List slots;
    private Date bookedDate;
	public Date getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}
}
