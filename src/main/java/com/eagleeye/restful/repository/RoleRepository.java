package com.eagleeye.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eagleeye.restful.model.Employee;
import com.eagleeye.restful.model.Role;
/**
 * 
 * @author BytesTree
 *
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	


	

}
