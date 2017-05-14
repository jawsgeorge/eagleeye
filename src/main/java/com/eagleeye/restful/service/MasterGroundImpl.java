package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.MasterGround;
import com.eagleeye.restful.repository.MasterGroundrepository;

@Service
public class MasterGroundImpl implements MasterGroundService {
	
	@Autowired
	private MasterGroundrepository masterGroundRepository;

	@Override
	public MasterGround save(MasterGround entity) {
		return masterGroundRepository.save(entity);
	}

	@Override
	public MasterGround getById(Serializable id) {
		return masterGroundRepository.findOne((Long) id);
	}

	@Override
	public List<MasterGround> getAll() {
		return masterGroundRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		masterGroundRepository.delete((Long) id);

	}

}
