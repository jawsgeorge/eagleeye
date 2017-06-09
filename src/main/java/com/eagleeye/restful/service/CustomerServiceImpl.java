package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.CustomerBooking;
import com.eagleeye.restful.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements Customerservice {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerBooking save(CustomerBooking entity) {
		return customerRepository.save(entity);
	}

	@Override
	public CustomerBooking getById(Serializable id) {
		return customerRepository.findOne( (Integer) id);
	}

	@Override
	public List<CustomerBooking> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		customerRepository.delete( (Integer) id);

	}

}
