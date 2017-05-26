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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1529346208416719886L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingReference;
	
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
	
	@Column(name="transaction_status", length=10)
	private String transactionStatus;
	
	@Column(name="pending_transaction", length=5)
	private int pendingTransaction;

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public int getPendingTransaction() {
		return pendingTransaction;
	}

	public void setPendingTransaction(int pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}

	public List<CustomerPayment> getCustomerPayment() {
		return customerPayment;
	}

	public void setCustomerPayment(List<CustomerPayment> customerPayment) {
		this.customerPayment = customerPayment;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public int getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(int bookingReference) {
		this.bookingReference = bookingReference;
	}

	@Override
	public String toString() {
		return "CustomerBooking [bookingReference=" + bookingReference + ", customerName=" + customerName
				+ ", mobileNumber=" + mobileNumber + ", Address=" + Address + ", amount=" + amount + ", slotBooking="
				+ slotBooking + ", customerPayment=" + customerPayment + ", transactionStatus=" + transactionStatus
				+ ", pendingTransaction=" + pendingTransaction + "]";
	}

	
	

	
}
