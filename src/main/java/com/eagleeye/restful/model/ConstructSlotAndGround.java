package com.eagleeye.restful.model;

import java.util.List;


public class ConstructSlotAndGround implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4137432568150232905L;
	private List<Ground> ground;
	
	public List<Ground> getGround() {
		return ground;
	}

	public void setGround(List<Ground> ground) {
		this.ground = ground;
	}

	public List<Slot> getSlot() {
		return slot;
	}

	public void setSlot(List<Slot> slot) {
		this.slot = slot;
	}

	private List<Slot> slot;
}
