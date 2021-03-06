package com.eagleeye.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eagleeye.restful.model.CustomerPayment;

@Repository
public interface PaymentRepository extends JpaRepository<CustomerPayment, Long> {

}
