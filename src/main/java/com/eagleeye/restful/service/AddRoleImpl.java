package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.Employee;
import com.eagleeye.restful.model.Role;
import com.eagleeye.restful.repository.RoleRepository;

@Service
public class AddRoleImpl implements AddRole {
	
	@Autowired
	private RoleRepository roleRepository;


	@Override
	public Role save(Role entity) {
		
		return roleRepository.save(entity);
	}

	@Override
	public Role getById(Serializable id) {
		return roleRepository.findOne((Long) id);
	}

	@Override
	public List getAll() {
		return roleRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		roleRepository.delete((Long) id);
		
	}
	

}
