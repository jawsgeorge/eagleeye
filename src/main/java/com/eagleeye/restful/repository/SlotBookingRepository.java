package com.eagleeye.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eagleeye.restful.model.SlotBooking;

@Repository
public interface SlotBookingRepository extends JpaRepository<SlotBooking, Integer> {

}
