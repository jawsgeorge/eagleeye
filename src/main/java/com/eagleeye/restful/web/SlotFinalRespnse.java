package com.eagleeye.restful.web;

import java.util.List;

public class SlotFinalRespnse implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4825421504589954711L;
	
	private List<SlotDetailsResponse> slotDetails;

	public List<SlotDetailsResponse> getSlotDetails() {
		return slotDetails;
	}

	public void setSlotDetails(List<SlotDetailsResponse> slotDetails) {
		this.slotDetails = slotDetails;
	} 

}
