package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.Slot;
import com.eagleeye.restful.repository.SlotRepository;

@Service
public class SlotImpl implements SlotService {
	
	@Autowired
	private SlotRepository slotRepository;

	@Override
	public Slot save(Slot entity) {
		return slotRepository.save(entity);
	}

	@Override
	public Slot getById(Serializable id) {
		return slotRepository.findOne((Long) id);
	}

	@Override
	public List<Slot> getAll() {
		return slotRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		slotRepository.delete((Long) id);

	}

}
