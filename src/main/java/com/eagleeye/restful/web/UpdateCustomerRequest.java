package com.eagleeye.restful.web;

import java.io.Serializable;
import java.util.List;

public class UpdateCustomerRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310805447604310008L;

	private List<UpdateCustomerDetails> customerPayment;

	public List<UpdateCustomerDetails> getCustomerPayment() {
		return customerPayment;
	}

	public void setCustomerPayment(List<UpdateCustomerDetails> customerPayment) {
		this.customerPayment = customerPayment;
	}
}
