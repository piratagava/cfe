package com.cfe.hernan.sistema.model.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.cfe.hernan.sistema.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class ClienteDto {

	private Long idCliente;
	private String username;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private boolean activo;
	private String password;
	private Set<Role> authorities = new HashSet<>();
	
	public Long getIdCliente() {
		return idCliente;
	}
	public String getUsername() {
		return username;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public boolean isActivo() {
		return activo;
	}
	public String getPassword() {
		return password;
	}
	public Set<Role> getAuthorities() {
		return authorities;
	}
	
	
}
