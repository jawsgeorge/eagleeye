package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDSlotBooking<SlotBooking> {
	
	SlotBooking save(SlotBooking entity);

	SlotBooking getById(Serializable id);

	List<SlotBooking> getAll();

	void delete(Serializable id);

}
