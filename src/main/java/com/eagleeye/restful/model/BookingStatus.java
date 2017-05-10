package com.eagleeye.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Booking_status")
public class BookingStatus implements java.io.Serializable {
	
	@Id
	@Column(name="booking_reference", length=10)
	private int bookingReference;
	
	@Column(name="transaction_status", length=10)
	private String transactionStatus;
	
	@Column(name="pending_transaction", length=5)
	private int pendingTransaction;

	public int getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(int bookingReference) {
		this.bookingReference = bookingReference;
	}

	

	public int getPendingTransaction() {
		return pendingTransaction;
	}

	public void setPendingTransaction(int pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "BookingStatus [bookingReference=" + bookingReference + ", transactionStatus=" + transactionStatus
				+ ", pendingTransaction=" + pendingTransaction + "]";
	}

	
	
	

}
