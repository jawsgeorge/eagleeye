package com.eagleeye.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eagleeye.restful.model.Ground;

@Repository
public interface GroundRepository extends JpaRepository<Ground, Long > {

}
