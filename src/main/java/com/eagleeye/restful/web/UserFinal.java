package com.eagleeye.restful.web;

import java.util.List;

import com.eagleeye.restful.model.User;

public class UserFinal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -408633346407613158L;
	
	private List<User> userList ;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	

}
