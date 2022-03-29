package com.cfe.hernan.sistema.model.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class ComunidadDto {

	private int idComunidad;
	private String direccion;

	public int getIdComunidad() {
		return idComunidad;
	}

	public String getDireccion() {
		return direccion;
	}

	@Override
	public String toString() {
		return "ComunidadDto [idComunidad=" + idComunidad + ", direccion=" + direccion + "]";
	}

}
