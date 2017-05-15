package com.eagleeye.restful.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_SLOT")
public class Slot implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2228797980453939898L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int slot_id;
	
	public int getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name="start_time", length=20)
	private String startTime;
	
	@Column(name="end_time", length=20)
	private String endTime;
	
	@OneToMany(mappedBy="slot",fetch=FetchType.LAZY)
	private Set<SlotBooking> slotBooking;

	public Set<SlotBooking> getSlotBooking() {
		return slotBooking;
	}

	public void setSlotBooking(Set<SlotBooking> slotBooking) {
		this.slotBooking = slotBooking;
	}


}
