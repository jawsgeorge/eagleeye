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

@Entity
@Table(name = "T_MENU")
public class Menu implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int menu_id;
	
	@Column(name="menuName", length=50)
	private String menuName;
	
	
//	@ManyToMany(fetch=FetchType.LAZY)
//	private Set<Role> role;

	

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	

	public String getMenuName() {
		return menuName;
	}

	@Override
	public String toString() {
		return "Menu [menu_id=" + menu_id + ", menuName=" + menuName + "]";
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	

	

}
