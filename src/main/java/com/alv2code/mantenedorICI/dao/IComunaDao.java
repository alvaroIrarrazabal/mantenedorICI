package com.alv2code.mantenedorICI.dao;

import java.util.List;

import com.alv2code.mantenedorICI.model.Comuna;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IComunaDao  extends CrudRepository<Comuna,Long> {
	
	@Query(value="SELECT * FROM ici.comuna  WHERE region_id = ?1",nativeQuery = true)
	public List<Comuna> findByRegion(Long id);

}
