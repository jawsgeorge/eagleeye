package com.eagleeye.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_CUSTOMER_PAYMENT")
public class CustomerPayment implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 653602222957056174L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payment_id;
	
	@Column(name="name",length=10)
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="amount",length=10)
	private int amount;
	
	@Column(name="mobileNumber",length=10)
	private long mobileNumber;
	
	@Column(name="mode",length=10)
	private String mode;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="bookingReference")
	@JsonIgnore
	private CustomerBooking customerBooking;

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
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

	public CustomerBooking getCustomerBooking() {
		return customerBooking;
	}

	public void setCustomerBooking(CustomerBooking customerBooking) {
		this.customerBooking = customerBooking;
	}

	@Override
	public String toString() {
		return "CustomerPayment [payment_id=" + payment_id + ", name=" + name + ", amount=" + amount + ", mobileNumber="
				+ mobileNumber + ", mode=" + mode + ", customerBooking=" + customerBooking + "]";
	}
	
	
	
	

}
