package com.eagleeye.restful.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "T_USER")
public class User implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1910216402809972403L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_id", unique = true, nullable = false)
	private Long user_id;
	
	@Column(name = "User_name", unique = true,length = 50)
	private String userName;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	@Column(name = "email", length = 100)
	private String email;
	
	
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="role_id")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@Column(name = "password")
	private String password;

	

}
