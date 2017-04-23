package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

public interface CRUDUser<User> {
	
	User save(User entity);

	User getById(Serializable id);

	List<User> getAll();

	void delete(Serializable id);

}
