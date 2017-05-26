package com.eagleeye.restful.web;

import java.util.List;

import com.eagleeye.restful.model.Role;

public class RoleFinal implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7579196759307475993L;
	
	private List<Role> RoleList;

	public List<Role> getRoleList() {
		return RoleList;
	}

	public void setRoleList(List<Role> roleList) {
		RoleList = roleList;
	}
	
	
	

}
