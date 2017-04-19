package com.eagleeye.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eagleeye.restful.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}
