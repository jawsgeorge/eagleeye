package com.eagleeye.restful.web;

import java.util.List;

import com.eagleeye.restful.model.SlotBooking;

public class SlotFinalRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2140246589033798219L;
	
	private List<SlotBooking> bookslots;

	public List<SlotBooking> getBookslots() {
		return bookslots;
	}

	public void setBookslots(List<SlotBooking> bookslots) {
		this.bookslots = bookslots;
	}

}
