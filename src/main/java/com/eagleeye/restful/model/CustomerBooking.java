package com.eagleeye.restful.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;





@Entity
@Table(name = "T_customer_Booking")
public class CustomerBooking implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	
	@Column(name="customerName",length=20)
	private String customerName;
	
	@Column(name="mobileNumber",length=12)
	private long mobileNumber;
	
	@Column(name="Address",length=255)
	private String Address;
	
	@Column(name="Amount",length=20)
	private int amount;
	
	@OneToMany(fetch=FetchType.EAGER)
	//@JoinColumn(name="book_id")
	private List<SlotBooking> slotBooking;
	
	@OneToMany(mappedBy="customerBooking",fetch=FetchType.LAZY)
	private List<CustomerPayment> customerPayment;

	public List<CustomerPayment> getCustomerPayment() {
		return customerPayment;
	}

	public void setCustomerPayment(List<CustomerPayment> customerPayment) {
		this.customerPayment = customerPayment;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	

	public List<SlotBooking> getSlotBooking() {
		return slotBooking;
	}

	public void setSlotBooking(List<SlotBooking> slotBooking) {
		this.slotBooking = slotBooking;
	}

	@Override
	public String toString() {
		return "CustomerBooking [customer_id=" + customer_id + ", customerName=" + customerName + ", mobileNumber="
				+ mobileNumber + ", Address=" + Address + ", amount=" + amount + ", slotBooking=" + slotBooking
				+ ", customerPayment=" + customerPayment + "]";
	}

	
}
