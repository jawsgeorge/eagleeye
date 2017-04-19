package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDMenu<Menu> {
	
	Menu save(Menu entity);

	Menu getById(Serializable id);

	List<Menu> getAll();

	void delete(Serializable id);

}
