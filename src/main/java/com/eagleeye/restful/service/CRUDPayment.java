package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDPayment<CustomerPayment> {
	
	CustomerPayment save(CustomerPayment entity);

	CustomerPayment getById(Serializable id);

	List<CustomerPayment> getAll();

	void delete(Serializable id);


}
