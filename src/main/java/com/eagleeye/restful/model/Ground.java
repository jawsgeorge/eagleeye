package com.eagleeye.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_GROUND")
public class Ground implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ground_id;
	
	@Column(name="groundName", length=20)
	private String groundName;
	
	@Column(name="size", length=5)
	private String size;
	
	@Column(name="city", length=20)
	private String city;
	
	@Column(name="place", length=20)
	private String place;
	
	@Column(name="type", length=20)
	private String type;

	public int getGround_id() {
		return ground_id;
	}

	public void setGround_id(int ground_id) {
		this.ground_id = ground_id;
	}

	public String getGroundName() {
		return groundName;
	}

	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Ground [ground_id=" + ground_id + ", groundName=" + groundName + ", size=" + size + ", city=" + city
				+ ", place=" + place + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((groundName == null) ? 0 : groundName.hashCode());
		result = prime * result + ground_id;
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ground other = (Ground) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (groundName == null) {
			if (other.groundName != null)
				return false;
		} else if (!groundName.equals(other.groundName))
			return false;
		if (ground_id != other.ground_id)
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	
}
