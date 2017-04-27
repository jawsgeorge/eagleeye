package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import com.eagleeye.restful.model.Ground;

public interface CRUDGround<Ground> {
	
	Ground save(Ground entity);

	Ground getById(Serializable id);

	List<Ground> getAll();

	void delete(Serializable id);

}
