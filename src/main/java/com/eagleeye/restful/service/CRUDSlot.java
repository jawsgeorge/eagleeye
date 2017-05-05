package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDSlot<Slot> {
	
	Slot save(Slot entity);

	Slot getById(Serializable id);

	List<Slot> getAll();

	void delete(Serializable id);


}
