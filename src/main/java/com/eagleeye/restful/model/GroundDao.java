package com.eagleeye.restful.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class GroundDao {
	
	 @PersistenceContext
	  private EntityManager entityManager;
	
	public List<Ground> getGroundByCityPlace(String city, String place){
		List<Ground> grounds = null;
		
		try{
		grounds  =   entityManager.createQuery(
		        "from Ground where city = :city and place = :place")
		        .setParameter("city", city)
		        .setParameter("place", place)
		        .getResultList();
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return  grounds;
	}
	
	public List<Ground> getGroundByCity(String city){
		List<Ground> grounds = null;
		
		try{
		grounds  =   entityManager.createQuery(
		        "from Ground where city = :city")
		        .setParameter("city", city)
		        .getResultList();
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return  grounds;
	}
	
	
	
	}
