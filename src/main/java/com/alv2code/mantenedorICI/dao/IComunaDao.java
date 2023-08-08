package com.alv2code.mantenedorICI.dao;

import java.util.List;

import com.alv2code.mantenedorICI.model.Comuna;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IComunaDao  extends CrudRepository<Comuna,Long> {
	
	@Query("SELECT c FROM Comuna c WHERE c.region.id = ?1 ")
	public List<Comuna> findByRegion(Long id);

}
