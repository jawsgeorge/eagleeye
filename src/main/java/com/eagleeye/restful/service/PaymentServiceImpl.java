package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.CustomerPayment;
import com.eagleeye.restful.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public CustomerPayment save(CustomerPayment entity) {
		return paymentRepository.save(entity);
	}

	@Override
	public CustomerPayment getById(Serializable id) {
		return paymentRepository.findOne((Long) id);
	}

	@Override
	public List<CustomerPayment> getAll() {
		return paymentRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		paymentRepository.delete((Long) id);

	}

}
