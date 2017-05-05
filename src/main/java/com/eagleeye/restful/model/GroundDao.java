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
	
	public List<Ground> getGroundByCity(String city, String place){
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
	
	//Method not used now. Hardcoded in UI.
	
//	public List<Ground> getLocationList(){
//		
//		
//		 List objs=null;
//		 objs=  entityManager.createQuery("select distinct city, place from Ground Order by city").getResultList();
//	     for(int i =0;i<objs.size();i++){
//	    	 //List gr = (List) ground;
//	    	// List gr = objs.get(i).
//	    	// System.out.println(gr.get(0));
//	    	// System.out.println(gr.get(1));
//	     }
//		 
//		 System.out.println("object list "+objs.toString());
//	     
//	    
//		 return objs;
//	}
	
	}
