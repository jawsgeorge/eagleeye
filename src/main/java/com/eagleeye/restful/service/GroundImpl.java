package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.Ground;
import com.eagleeye.restful.repository.GroundRepository;

@Service
public class GroundImpl implements GroundService {
	
	@Autowired
	private GroundRepository groundRepository;

	@Override
	public Ground save(Ground entity) {
		return groundRepository.save(entity);
	}

	@Override
	public Ground getById(Serializable id) {
		return groundRepository.findOne((Long) id);
	}

	@Override
	public List<Ground> getAll() {
		return groundRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		groundRepository.delete((Long) id);

	}

}
