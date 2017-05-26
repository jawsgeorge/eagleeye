package com.eagleeye.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_GROUND")
public class Ground implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5280363549630982085L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ground_id;
	
	@Column(name="groundName", length=20)
	private String groundName;
	
	@Column(name="size", length=5)
	private String size;
	
	
	@Column(name="type", length=20)
	private String type;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="masterGroundId")
	private MasterGround masterGround;
	

	public MasterGround getMasterGround() {
		return masterGround;
	}

	public void setMasterGround(MasterGround masterGround) {
		this.masterGround = masterGround;
	}

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

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Ground [ground_id=" + ground_id + ", groundName=" + groundName + ", size=" + size + ", type=" + type
				+ ", masterGround=" + masterGround + "]";
	}

	
	
	
	
	
}
