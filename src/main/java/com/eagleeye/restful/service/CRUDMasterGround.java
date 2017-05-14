package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDMasterGround<MasterGround> {
	
	MasterGround save(MasterGround entity);

	MasterGround getById(Serializable id);

	List<MasterGround> getAll();

	void delete(Serializable id);


}
