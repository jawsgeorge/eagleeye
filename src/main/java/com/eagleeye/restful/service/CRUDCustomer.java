package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDCustomer<CustomerBooking> {
	
	CustomerBooking save(CustomerBooking entity);

	CustomerBooking getById(Serializable id);

	List<CustomerBooking> getAll();

	void delete(Serializable id);

}
