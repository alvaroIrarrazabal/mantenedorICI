package com.alv2code.mantenedorICI.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Entity
@Table(name="rol")
@Data
public class Rol  implements Serializable{

	

	
	private static final long serialVersionUID = 8746802628373013207L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	

        

//@ManyToMany(mappedBy = "roles")
//	private List<Persona> persona;
	 
       
       
}
