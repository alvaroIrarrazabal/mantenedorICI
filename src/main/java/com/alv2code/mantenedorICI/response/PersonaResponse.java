package com.alv2code.mantenedorICI.response;

import java.util.List;

import com.alv2code.mantenedorICI.model.Persona;

import lombok.Data;

public class PersonaResponse {
	
	private List<Persona> persona;

	public List<Persona> getPersona() {
		return persona;
	}

	public void setPersona(List<Persona> persona) {
		this.persona = persona;
	}
	
	

}
