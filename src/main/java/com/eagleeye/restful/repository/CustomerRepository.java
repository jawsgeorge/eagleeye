package com.eagleeye.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eagleeye.restful.model.CustomerBooking;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerBooking, Long> {

}
