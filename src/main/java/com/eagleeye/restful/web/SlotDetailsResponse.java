package com.eagleeye.restful.web;

import java.util.List;

public class SlotDetailsResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 976945233940379216L;
	
	private String groundConfigCode;
	private boolean isSubGround;
	private String groundName;
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	public boolean getIsSubGround() {
		return isSubGround;
	}
	public void setIsSubGround(boolean isSubGround) {
		this.isSubGround = isSubGround;
	}
	public String getGroundConfigCode() {
		return groundConfigCode;
	}
	public void setGroundConfigCode(String groundConfigCode) {
		this.groundConfigCode = groundConfigCode;
	}
	public long getMasterGroundId() {
		return masterGroundId;
	}
	public void setMasterGroundId(long masterGroundId) {
		this.masterGroundId = masterGroundId;
	}
	public String getGroundData() {
		return groundData;
	}
	public void setGroundData(String groundData) {
		this.groundData = groundData;
	}
	public List<SlotResponse> getSlots() {
		return slots;
	}
	public void setSlots(List<SlotResponse> slots) {
		this.slots = slots;
	}
	private long masterGroundId;
	private String groundData;
	private List<SlotResponse> slots;
	private long groundId;
	public long getGroundId() {
		return groundId;
	}
	public void setGroundId(long groundId) {
		this.groundId = groundId;
	}

}
