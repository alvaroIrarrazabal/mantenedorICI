package com.alv2code.mantenedorICI.dao;

import com.alv2code.mantenedorICI.model.Persona;

import org.springframework.data.repository.CrudRepository;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

}
