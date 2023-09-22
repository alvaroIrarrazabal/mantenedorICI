package com.alv2code.mantenedorICI.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="persona")
public class Persona implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 3919183167791648275L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private String telefono;
	private Date   cumple;
	@ManyToOne(fetch=FetchType.EAGER )
	private Pais   pais;
	@ManyToOne(fetch=FetchType.EAGER )
	private Region region;
	@ManyToOne(fetch=FetchType.EAGER )
	private Comuna comuna;
	private int   supervisorId;
	private String nombreSupervisor;
	private String apellidoPaternoSupervisor;
	private String apellidoMaternoSupervisor;
	
	private String genero;
	private String estadoCivil;
	private String redSocial;
	private int    conyugeId;
	private int    padreId;
	private int    madreId; 
	private int    ocupacionId;
	
	
	
	
	
	
	
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getCumple() {
		return cumple;
	}

	public void setCumple(Date cumple) {
		this.cumple = cumple;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getNombreSupervisor() {
		return nombreSupervisor;
	}

	public void setNombreSupervisor(String nombreSupervisor) {
		this.nombreSupervisor = nombreSupervisor;
	}

	public String getApellidoPaternoSupervisor() {
		return apellidoPaternoSupervisor;
	}

	public void setApellidoPaternoSupervisor(String apellidoPaternoSupervisor) {
		this.apellidoPaternoSupervisor = apellidoPaternoSupervisor;
	}

	public String getApellidoMaternoSupervisor() {
		return apellidoMaternoSupervisor;
	}

	public void setApellidoMaternoSupervisor(String apellidoMaternoSupervisor) {
		this.apellidoMaternoSupervisor = apellidoMaternoSupervisor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getRedSocial() {
		return redSocial;
	}

	public void setRedSocial(String redSocial) {
		this.redSocial = redSocial;
	}

	public int getConyugeId() {
		return conyugeId;
	}

	public void setConyugeId(int conyugeId) {
		this.conyugeId = conyugeId;
	}

	public int getPadreId() {
		return padreId;
	}

	public void setPadreId(int padreId) {
		this.padreId = padreId;
	}

	public int getMadreId() {
		return madreId;
	}

	public void setMadreId(int madreId) {
		this.madreId = madreId;
	}

	public int getOcupacionId() {
		return ocupacionId;
	}

	public void setOcupacionId(int ocupacionId) {
		this.ocupacionId = ocupacionId;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany(fetch=FetchType.EAGER , cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
     private List<Rol> roles;
     
     public void addRol(Rol rol){
         if(this.roles == null){
             this.roles = new ArrayList<>();
         }
         
         this.roles.add(rol);
     }
     
     


}
