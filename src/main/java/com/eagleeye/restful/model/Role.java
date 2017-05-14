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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_ROLE")
public class Role implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Role_id", unique = true, nullable = false)
	private Long role_id;
	
	@Column(name = "Role_name", length = 50)
	private String roleName;
	
	@Column(name = "Description", length = 100)
	private String description;
	
	@ManyToMany(fetch=FetchType.EAGER)	
	//@JoinColumn(name="menu_id")
	private Set<Menu> menu;
	
	@JsonIgnore
	@OneToMany(mappedBy="role",fetch=FetchType.EAGER)
	private Set<User> user;

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Menu> getMenu() {
		return menu;
	}

	public void setMenu(Set<Menu> menu) {
		this.menu = menu;
	}

	
	

	

	

}
