package com.eagleeye.restful.service;

/**
 * @author BytesTree
 */
import java.io.Serializable;
import java.util.List;

public interface CRUDRole<Role> {

	Role save(Role entity);

	Role getById(Serializable id);

	List<Role> getAll();

	void delete(Serializable id);
}
