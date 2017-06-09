package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.SlotBooking;
import com.eagleeye.restful.repository.SlotBookingRepository;
import com.eagleeye.restful.repository.SlotRepository;

@Service
public class SlotBookingImpl implements SlotBookingService {

	@Autowired
	private SlotBookingRepository slotBooking;
	
	@Override
	public SlotBooking save(SlotBooking entity) {
		return slotBooking.save(entity);
	}

	@Override
	public SlotBooking getById(Serializable id) {
		return slotBooking.findOne( (Integer) id);
	}

	@Override
	public List<SlotBooking> getAll() {
		return slotBooking.findAll();
	}

	@Override
	public void delete(Serializable id) {
		slotBooking.delete( (Integer) id);

	}

}
