package com.eagleeye.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagleeye.restful.model.User;
import com.eagleeye.restful.repository.UserRepository;


@Service
public class UserImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User entity) {
		
		return userRepository.save(entity);
	}

	@Override
	public User getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

}
