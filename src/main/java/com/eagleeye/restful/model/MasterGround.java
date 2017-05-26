package com.eagleeye.restful.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_master_ground")
public class MasterGround implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7372761696758889063L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "master_ground_id", unique = true, nullable = false)
	private int masterGroundId;
	
	@Column(name="masterGroundName", length=20)
	private String masterGroundName;
	
	@Column(name="city", length=20)
	private String city;
	
	@Column(name="place", length=20)
	private String place;
	
	@OneToMany(mappedBy="masterGround",fetch=FetchType.EAGER)
	private Set<Ground> grounds;

	public int getMasterGroundId() {
		return masterGroundId;
	}

	public void setMasterGroundId(int masterGroundId) {
		this.masterGroundId = masterGroundId;
	}

	public String getMasterGroundName() {
		return masterGroundName;
	}

	public void setMasterGroundName(String masterGroundName) {
		this.masterGroundName = masterGroundName;
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

	public Set<Ground> getGrounds() {
		return grounds;
	}

	public void setGrounds(Set<Ground> grounds) {
		this.grounds = grounds;
	}

	@Override
	public String toString() {
		return "MasterGround [masterGroundId=" + masterGroundId + ", masterGroundName=" + masterGroundName + ", city="
				+ city + ", place=" + place + ", grounds=" + grounds + "]";
	}
	
	

}
