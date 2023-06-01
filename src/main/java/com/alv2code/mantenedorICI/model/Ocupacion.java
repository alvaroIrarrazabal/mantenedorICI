package com.alv2code.mantenedorICI.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ocupacion")
@Data
public class Ocupacion implements Serializable {/**
	 *
	 */
	private static final long serialVersionUID = -5881695747152373732L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	

}
