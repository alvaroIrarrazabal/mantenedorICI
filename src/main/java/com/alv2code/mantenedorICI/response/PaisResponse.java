package com.alv2code.mantenedorICI.response;

import java.util.List;

import com.alv2code.mantenedorICI.model.Comuna;
import com.alv2code.mantenedorICI.model.Pais;

import lombok.Data;


public class PaisResponse {
	
	private List<Pais> pais;

	public List<Pais> getPais() {
		return pais;
	}

	public void setPais(List<Pais> pais) {
		this.pais = pais;
	}
	
	    

}
