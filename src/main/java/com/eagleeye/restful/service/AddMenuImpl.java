package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.Menu;
import com.eagleeye.restful.repository.MenuRepository;
import com.eagleeye.restful.repository.RoleRepository;

@Service
public class AddMenuImpl implements AddMenu {
	
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu save(Menu entity) {
		
		return menuRepository.save(entity);
	}

	@Override
	public Menu getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

}
