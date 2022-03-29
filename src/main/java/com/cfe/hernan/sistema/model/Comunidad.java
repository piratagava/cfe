package com.cfe.hernan.sistema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comunidad")
public class Comunidad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_comunidad")
	private int idComunidad;

	@Column(name = "direccion")
	@NotNull(message = "Campo direccion no debe ir vacio a la base")
	private String direccion;

	public Comunidad() {
		Comunidad comunidad;
	}

	public Comunidad(int idComunidad,
			@NotNull(message = "Campo direccion no debe ir vacio a la base") String direccion) {
		super();
		this.idComunidad = idComunidad;
		this.direccion = direccion;
	}

	public int getIdComunidad() {
		return idComunidad;
	}

	public void setIdComunidad(int idComunidad) {
		this.idComunidad = idComunidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Comunidad [idComunidad=" + idComunidad + ", direccion=" + direccion + "]";
	}
}
