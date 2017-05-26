package com.eagleeye.restful.web;

import java.util.List;

import com.eagleeye.restful.model.MasterGround;

public class MasterGroundFinal implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4745175257973969200L;
	
	private List<MasterGround> mastergroundList;

	public List<MasterGround> getMastergroundList() {
		return mastergroundList;
	}

	public void setMastergroundList(List<MasterGround> mastergroundList) {
		this.mastergroundList = mastergroundList;
	}
	
	

}
