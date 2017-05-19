package com.eagleeye.restful.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_SLOT_BOOKING")
public class SlotBooking implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8342504997604011667L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;
	
	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public int getGroundId() {
		return groundId;
	}

	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="date")
	private Date date;
	
	
	
	@Column(name="ground_id")
	private int groundId;
	
	@Column(name="status", length=20)
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumn(name="slot_id")
	private Slot slot;

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}
}
