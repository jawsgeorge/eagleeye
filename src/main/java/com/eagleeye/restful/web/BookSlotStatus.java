package com.eagleeye.restful.web;

import java.util.List;

import com.eagleeye.restful.model.SlotBooking;

public class BookSlotStatus implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5738972840087543908L;
 private String bookStatus;
 public String getBookStatus() {
	return bookStatus;
}
public void setBookStatus(String bookStatus) {
	this.bookStatus = bookStatus;
}
public List<SlotBooking> getBookedSlots() {
	return bookedSlots;
}
public void setBookedSlots(List<SlotBooking> bookedSlots) {
	this.bookedSlots = bookedSlots;
}
private List<SlotBooking> bookedSlots;
}
