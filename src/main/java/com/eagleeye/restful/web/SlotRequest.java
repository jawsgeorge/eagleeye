package com.eagleeye.restful.web;

public class SlotRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7784829290636015867L;
	
	private String bookDate;
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	private int groundId;
	public int getGroundId() {
		return groundId;
	}
	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}
	private long masterGroundId;
	public long getMasterGroundId() {
		return masterGroundId;
	}
	public void setMasterGroundId(long masterGroundId) {
		this.masterGroundId = masterGroundId;
	}
	private String groundName;
	
	private int slotId;
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	
}
