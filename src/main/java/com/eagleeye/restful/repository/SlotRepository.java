package com.eagleeye.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.eagleeye.restful.model.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long > {

}
